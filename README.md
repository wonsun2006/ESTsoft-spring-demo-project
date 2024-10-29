# DB 설정 적용
## application.properties
```properties
spring.datasource.password=ENC(4QDqSA8NttU7qJ+bs6CeubY8fm4/yz9oWPPRbbioUSIsoihEe48uizrgvALHEhnq)
...
jasypt.encryptor.password=${ENCRYPT_KEY}
```

- MySQL 유저 이름과 비밀번호 설정 필요
- ```spring.datasource.password``` : ENC(Jasypt암호화값)
  - Jasypt 모듈이 암호화 값을 복호화하여 관리
- ```jasypt.encryptor.password``` : 암호화 키 값 (환경변수로 입력)
  - ENCRYPT_KEY 환경변수에 암호화 키 값을 입력
  - IntelliJ 실행 : bootRun 설정에서 ENCRYPT_KEY=키값 설정 후 실행
  - 빌드파일 실행 : java -DENCRYPT_KEY=키값 -jar 빌드파일.jar
