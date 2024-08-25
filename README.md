# ClassCard Auto Test

영어 단어장 서비스인 [ClassCard](https://classcard.net) 에서 자동으로 테스트를 볼 수 있는 매크로입니다.

## 사용법

1. `com.github.ioloolo.classcard.factory.Account` 클래스 내부에서 계정을 등록해주세요.
```java
@Data(staticConstructor = "of")
public final class Account {

    private final String id;
    private final String pw;

    public static final class Provider {
        public static Account get(User user) {
            return switch (user) {
                // case <별칭> -> Account.of(<아이디>, <비밀번호>);
                case HONG_GILDONG -> Account.of("honggildong1", "p4ssw0rd");
            };
        }

        public enum User {
            <별칭 1>
        }
    }
}
```

2. `com.github.ioloolo.classcard.ClassCard` 클래스 내부에서 사용할 계정을 수정 후, 실행시켜주세요.
```java
public final class ClassCard {
    public static void main(String[] args) {
        ClassCard classCard = new ClassCard();

        classCard.login(Provider.get(NAME));

        classCard.selectClass();
        classCard.selectCard();
        classCard.test();
    }

    // ....
}
```
