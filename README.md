## 프로젝트 개요

건강검진 데이터를 기반으로 비만 여부를 예측하는 AI 웹 서비스입니다.

사용자는 키와 몸무게 등 건강 정보를 입력하여 비만 여부를 예측할 수 있으며,
Spring Boot 서버가 Flask AI 서버와 통신하여 예측 결과를 제공합니다.

본 프로젝트는 Spring Boot 기반 웹 서비스와 Flask 기반 AI 서버를 분리하여 구성하고,
REST API 통신, Spring Security 인증, JPA 기반 데이터 처리, 머신러닝 모델 활용 과정을
학습하기 위해 제작되었습니다.

## 주요 기능

- 회원가입 및 로그인 기능
- Spring Security 기반 인증 처리
- 게시판 CRUD 기능
- 건강 정보 입력 기능
- Flask AI 서버 연동
- 건강검진 데이터 기반 비만 여부 예측
- 비회원 예측 기능 지원
- 예측 결과 화면 출력

## 사용자 흐름

1. 사용자는 메인 화면에서 키와 몸무게 등 건강 정보를 입력합니다.
2. Spring Boot 서버가 입력 데이터를 Flask AI 서버로 전달합니다.
3. Flask 서버는 머신러닝 모델을 통해 비만 여부를 예측합니다.
4. 예측 결과가 Spring Boot 서버로 반환됩니다.
5. 사용자는 웹 화면에서 예측 결과를 확인할 수 있습니다.

## 시스템 구조

```text
사용자
  |
  v
Thymeleaf 화면
  |
  v
Spring Boot 서버
  |-- 회원가입 / 로그인 / 게시판
  |-- Spring Security 인증 처리
  |-- JPA를 통한 MySQL 데이터 처리
  |
  v
Flask AI 서버
  |
  v
Scikit-learn ML 모델
```

## 기술 스택

| 구분 | 기술 |
| --- | --- |
| Backend | Spring Boot, Spring Security, JPA |
| Frontend | Thymeleaf, Bootstrap |
| AI Server | Flask |
| Machine Learning | Scikit-learn |
| Database | MySQL |
| Build Tool | Gradle |
| Version Control | Git, GitHub |

## 주요 화면

| 화면 | 설명 |
| --- | --- |
| `main-before-login.jpg` | 로그인 전 메인 화면 |
| `main-after-login.jpg` | 로그인 후 메인 화면 |
| `login-page.jpg` | 로그인 화면 |
| `predict-result.jpg` | 비만 예측 결과 화면 |
| `question-list.jpg` | 게시글 목록 화면 |
| `question-detail.jpg` | 게시글 상세 화면 |
| `answer-page.jpg` | 답변 작성 화면 |
| `member-list.jpg` | 회원 목록 화면 |
| `search-page.jpg` | 검색 화면 |

## 화면 캡처

```text
images/
|-- main-before-login.jpg
|-- main-after-login.jpg
|-- login-page.jpg
|-- predict-result.jpg
|-- question-list.jpg
|-- question-detail.jpg
|-- answer-page.jpg
|-- member-list.jpg
`-- search-page.jpg
```

## 포트폴리오 강조 포인트

- Spring Boot와 Flask 서버를 분리하여 AI API 서버 구조를 구현했습니다.
- REST API 기반으로 Spring Boot 서버와 Flask 서버 간 통신을 구현했습니다.
- Spring Security를 활용하여 로그인 인증 구조를 적용했습니다.
- 건강검진 공공데이터를 활용하여 머신러닝 예측 모델을 구축했습니다.
- 비회원도 사용할 수 있는 AI 예측 기능을 구현했습니다.
- 웹 서버와 AI 서버를 분리하여 실제 AI 서비스 구조에 가까운 형태로 설계했습니다.

## 향후 개선 사항

- 예측 결과 DB 저장 기능 추가
- 로그인 사용자의 예측 이력 조회 기능 추가
- 예측 입력값 검증 강화
- Flask 서버 장애 발생 시 예외 처리 개선
- 모델 성능 지표 및 학습 과정 문서화
- Docker를 활용한 Spring Boot, Flask, MySQL 실행 환경 구성
