from __future__ import annotations

import argparse
from pathlib import Path

import joblib
import pandas as pd
from sklearn.compose import ColumnTransformer
from sklearn.impute import SimpleImputer
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score, classification_report
from sklearn.model_selection import train_test_split
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import StandardScaler


DEFAULT_DATA_PATH = (
    r"C:\Users\두드림컴퓨터학원\Documents\HJKIM\machine\data"
    r"\국민건강보험공단_건강검진정보_2024.CSV"
)
DEFAULT_MODEL_PATH = Path("models") / "obesity_model.joblib"

HEIGHT_COLUMN = "신장(5cm단위)"
WEIGHT_COLUMN = "체중(5kg단위)"
FEATURE_COLUMNS = ["height", "weight"]
TARGET_COLUMN = "is_obese"
OBESITY_BMI_THRESHOLD = 25.0


def read_health_checkup_csv(path: str | Path) -> pd.DataFrame:
    encodings = ["utf-8-sig", "utf-8", "cp949"]
    last_error: UnicodeDecodeError | None = None

    for encoding in encodings:
        try:
            return pd.read_csv(path, encoding=encoding)
        except UnicodeDecodeError as error:
            last_error = error

    raise ValueError(f"CSV 파일 인코딩을 확인할 수 없습니다: {path}") from last_error


def preprocess_data(raw_data: pd.DataFrame) -> pd.DataFrame:
    required_columns = [HEIGHT_COLUMN, WEIGHT_COLUMN]
    missing_columns = [column for column in required_columns if column not in raw_data.columns]
    if missing_columns:
        raise ValueError(f"필수 컬럼이 없습니다: {missing_columns}")

    data = raw_data[required_columns].copy()
    data = data.rename(columns={HEIGHT_COLUMN: "height", WEIGHT_COLUMN: "weight"})
    data["height"] = pd.to_numeric(data["height"], errors="coerce")
    data["weight"] = pd.to_numeric(data["weight"], errors="coerce")
    data = data.dropna(subset=FEATURE_COLUMNS)

    data = data[(data["height"] > 0) & (data["weight"] > 0)]
    data["bmi"] = data["weight"] / ((data["height"] / 100) ** 2)
    data[TARGET_COLUMN] = (data["bmi"] >= OBESITY_BMI_THRESHOLD).astype(int)

    return data[FEATURE_COLUMNS + [TARGET_COLUMN, "bmi"]]


def build_model() -> Pipeline:
    numeric_preprocessor = Pipeline(
        steps=[
            ("imputer", SimpleImputer(strategy="median")),
            ("scaler", StandardScaler()),
        ]
    )

    preprocessor = ColumnTransformer(
        transformers=[
            ("numeric", numeric_preprocessor, FEATURE_COLUMNS),
        ]
    )

    return Pipeline(
        steps=[
            ("preprocessor", preprocessor),
            ("classifier", LogisticRegression(max_iter=1000, random_state=42)),
        ]
    )


def train(data_path: str | Path, model_path: str | Path) -> None:
    raw_data = read_health_checkup_csv(data_path)
    data = preprocess_data(raw_data)

    x = data[FEATURE_COLUMNS]
    y = data[TARGET_COLUMN]

    x_train, x_test, y_train, y_test = train_test_split(
        x,
        y,
        test_size=0.2,
        random_state=42,
        stratify=y,
    )

    model = build_model()
    model.fit(x_train, y_train)

    predictions = model.predict(x_test)
    accuracy = accuracy_score(y_test, predictions)
    report = classification_report(
        y_test,
        predictions,
        target_names=["normal", "obese"],
        zero_division=0,
    )

    artifact = {
        "model": model,
        "feature_columns": FEATURE_COLUMNS,
        "target_column": TARGET_COLUMN,
        "obesity_bmi_threshold": OBESITY_BMI_THRESHOLD,
        "label_map": {
            0: "정상",
            1: "비만",
        },
        "metrics": {
            "accuracy": accuracy,
            "classification_report": report,
        },
    }

    model_path = Path(model_path)
    model_path.parent.mkdir(parents=True, exist_ok=True)
    joblib.dump(artifact, model_path)

    print(f"학습 데이터 수: {len(data)}")
    print(f"모델 저장 경로: {model_path}")
    print(f"정확도: {accuracy:.4f}")
    print(report)


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description="건강검진 데이터 기반 비만 예측 모델 학습")
    parser.add_argument(
        "--data-path",
        default=DEFAULT_DATA_PATH,
        help="건강검진 CSV 파일 경로",
    )
    parser.add_argument(
        "--model-path",
        default=str(DEFAULT_MODEL_PATH),
        help="저장할 joblib 모델 파일 경로",
    )
    return parser.parse_args()


if __name__ == "__main__":
    args = parse_args()
    train(args.data_path, args.model_path)
