# 개발 계획서

## 개발 일정표

| 단계 | 작업 영역 | 주요 작업 | 예상 산출물 |
| --- | --- | --- | --- |
| 1단계 | 프로젝트 초기 설정 | Spring Boot 프로젝트 생성, Flask 프로젝트 생성, GitHub 저장소 생성, 문서 작성 | 기본 프로젝트 구조, README, 요구사항 문서 |
| 2단계 | 백엔드 기본 기능 구현 | 회원가입, 로그인, Spring Security, 게시판 CRUD 구현 | 회원 기능, 인증 기능, 게시판 기능 |
| 3단계 | AI 서버 구현 | 건강검진 데이터 전처리, 머신러닝 모델 학습, Flask 예측 API 구현 | 학습 모델, Flask API 서버 |
| 4단계 | 서버 연동 | Spring에서 Flask API 요청, JSON 데이터 처리, 예측 결과 화면 출력 | Spring-Flask 연동 기능 |
| 5단계 | 프론트 UI 개선 | Bootstrap 반응형 적용, 메인 페이지 구성, 예측 화면 UI 개선 | 사용자 화면, 반응형 UI |
| 6단계 | 테스트 및 배포 | 기능 테스트, 예외 처리, AWS 배포, GitHub README 정리 | 배포 서비스, 최종 문서 |

---

## 1단계 - 프로젝트 초기 설정

### 작업 내용

- Spring Boot 프로젝트를 생성한다.
- Flask AI 서버 프로젝트를 생성한다.
- GitHub 저장소를 생성한다.
- 프로젝트 기본 폴더 구조를 정리한다.
- README 문서를 작성한다.
- 요구사항 정의서를 작성한다.
- 개발 계획서를 작성한다.

### 주요 산출물

- Spring Boot 프로젝트
- Flask 프로젝트
- GitHub 저장소
- `README.md`
- `docs/requirements.md`
- `docs/development-plan.md`

---

## 2단계 - 백엔드 기본 기능 구현

### 작업 내용

- 회원가입 기능을 구현한다.
- 로그인 기능을 구현한다.
- Spring Security를 적용한다.
- 비밀번호 암호화를 적용한다.
- 게시판 CRUD 기능을 구현한다.
- 로그인 사용자만 게시글을 작성할 수 있도록 제한한다.

### 주요 산출물

- 회원가입 화면
- 로그인 화면
- 로그아웃 기능
- 게시글 목록 화면
- 게시글 상세 화면
- 게시글 작성 화면
- 게시글 수정 및 삭제 기능

### 관련 클래스

- `SecurityConfig`
- `Member`
- `MemberRepository`
- `MemberService`
- `MemberController`
- `Question`
- `QuestionRepository`
- `QuestionService`
- `QuestionController`
- `Answer`
- `AnswerRepository`
- `AnswerService`
- `AnswerController`

---

## 3단계 - AI 서버 구현

### 작업 내용

- 건강검진 데이터를 수집한다.
- 데이터 전처리를 수행한다.
- 비만 여부 예측 모델을 학습한다.
- 학습된 모델을 파일로 저장한다.
- Flask 서버에서 모델을 로드한다.
- 예측 API를 구현한다.
- 서버 상태 확인 API를 구현한다.

### 주요 산출물

- 전처리된 학습 데이터
- 머신러닝 모델 파일
- Flask 예측 API
- Flask 서버 실행 파일

### 관련 파일

- `app.py`
- `requirements.txt`
- `model.pkl`
- `scaler.pkl`
- `prediction_service.py`

### Flask API

| 기능 | Method | URL | 설명 |
| --- | --- | --- | --- |
| 서버 상태 확인 | GET | `/health` | Flask 서버 실행 상태 확인 |
| 비만 예측 | POST | `/predict` | 키와 몸무게 기반 비만 여부 예측 |

---

## 4단계 - 서버 연동

### 작업 내용

- Spring Boot 서버에서 Flask API로 요청을 보낸다.
- 키와 몸무게 데이터를 JSON 형식으로 전달한다.
- Flask 서버에서 반환한 JSON 응답을 Spring Boot에서 처리한다.
- 예측 결과를 Thymeleaf 화면에 출력한다.
- Flask 서버 연결 실패 시 예외 처리를 추가한다.

### 주요 산출물

- Spring-Flask API 연동 기능
- 예측 요청 DTO
- 예측 응답 DTO
- 예측 결과 화면

### 관련 클래스

- `PredictController`
- `PredictService`
- `FlaskApiClient`
- `PredictRequest`
- `PredictResponse`

### 처리 흐름

```text
사용자 입력
  -> Spring Boot Controller
  -> Spring Boot Service
  -> Flask API Client
  -> Flask AI Server
  -> ML Model
  -> Flask Response
  -> Spring Boot
  -> Thymeleaf Result Page
```

---

## 5단계 - 프론트 UI 개선

### 작업 내용

- Bootstrap을 적용하여 반응형 UI를 구성한다.
- 메인 페이지를 포트폴리오용으로 개선한다.
- 로그인 전후 화면을 구분한다.
- 예측 입력 화면을 개선한다.
- 예측 결과 화면을 개선한다.
- 게시판 화면의 가독성을 개선한다.

### 주요 산출물

- 메인 화면
- 로그인 화면
- 회원가입 화면
- 예측 입력 화면
- 예측 결과 화면
- 게시판 목록 화면
- 게시판 상세 화면

### 관련 화면

- `main-before-login.jpg`
- `main-after-login.jpg`
- `login-page.jpg`
- `predict-result.jpg`
- `question-list.jpg`
- `question-detail.jpg`
- `answer-page.jpg`
- `search-page.jpg`

---

## 6단계 - 테스트 및 배포

### 작업 내용

- 회원가입 및 로그인 기능을 테스트한다.
- 게시판 CRUD 기능을 테스트한다.
- Flask 예측 API를 테스트한다.
- Spring-Flask 연동 기능을 테스트한다.
- 예외 상황을 점검한다.
- AWS 배포를 진행한다.
- GitHub README를 최종 정리한다.

### 주요 산출물

- 테스트 결과
- 배포된 서비스
- 최종 README 문서
- 프로젝트 화면 캡처
- 포트폴리오 발표 자료

### 테스트 항목

- 회원가입 성공 여부
- 로그인 및 로그아웃 동작 여부
- 게시글 작성, 조회, 수정, 삭제 동작 여부
- 비회원 예측 기능 동작 여부
- Flask 서버 예측 결과 반환 여부
- Spring 서버의 Flask API 호출 여부
- 예측 결과 화면 출력 여부
- 잘못된 입력값에 대한 예외 처리 여부

---

## 최종 작업 순서 요약

1. 프로젝트 구조를 생성한다.
2. README와 요구사항 문서를 작성한다.
3. 회원가입과 로그인 기능을 구현한다.
4. Spring Security 인증 구조를 적용한다.
5. 게시판 CRUD 기능을 구현한다.
6. 건강검진 데이터를 전처리한다.
7. 머신러닝 모델을 학습한다.
8. Flask 예측 API를 구현한다.
9. Spring Boot에서 Flask API를 호출한다.
10. 예측 결과를 화면에 출력한다.
11. Bootstrap 기반 UI를 개선한다.
12. 전체 기능을 테스트한다.
13. AWS에 배포한다.
14. GitHub README와 포트폴리오 문서를 최종 정리한다.
