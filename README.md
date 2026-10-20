# ClassCard-Auto-Test

> 클래스카드 단어 시험 자동 응시 매크로 (Java)

영어 단어장 서비스 [클래스카드](https://www.classcard.net)의 단어 시험을 자동으로 응시하는 Selenium 매크로다. 로그인 후 콘솔에서 클래스와 단어 세트를 고르면, 세트의 단어 데이터를 페이지에서 읽어 두고 시험 문제마다 맞는 보기를 클릭한다. 2023년에 만들었다.

## 주요 기능
- Chrome으로 클래스카드에 로그인
- 내 클래스 목록과 클래스의 단어 세트 목록을 콘솔에 띄우고 번호로 선택(세트 선택에서 0을 입력하면 클래스 선택으로 돌아감)
- 세트 페이지의 `study_data`에서 영어·한국어 단어 쌍을 수집
- 시험 화면에서 문제 단어와 짝이 맞는 보기를 찾아 자동 클릭

## 기술 스택
| 영역 | 기술 |
|---|---|
| 언어 | Java, Gradle 8.2 |
| 라이브러리 | Selenium 4.23.1 (ChromeDriver), Lombok 1.18.34 |

## 구조
```
src/main/java/com/github/ioloolo/classcard/
├── ClassCard.java         진입점, 로그인·클래스/세트 선택·시험 응시
├── factory/Account.java   계정 별칭과 아이디·비밀번호 등록
├── factory/Xpath.java     페이지 요소 XPath
└── my/                    클래스·단어 세트·단어 모델
```

## 실행
```bash
./gradlew build
```
1. `factory/Account.java`의 `Provider.User` enum에 별칭을 추가하고 `Account.of(<아이디>, <비밀번호>)`로 계정을 등록한다.
2. `ClassCard.main`의 `Provider.get(...)`에 사용할 별칭을 넣고 실행한다. Chrome이 설치되어 있어야 한다.

---
2023 · 개인 프로젝트 · 보관됨
