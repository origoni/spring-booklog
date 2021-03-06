# spring-booklog

책에서 기억에 남는 구절을 저장할 수 있습니다.

### 이 프로젝트의 핵심은.. 오픈 API 사용 입니다.
카카오와 구글의 API를 사용하여 책을 검색하고 서고에 추가한 뒤, 페이지 번호를 적고 글을 작성하면 됩니다.

---

### Quick Start
설치되어 있어야 할 것
- JDK 1.8 (or Java 11)
- Maven 3.6
- Git

```
git clone https://github.com/origoni/spring-booklog.git
cd spring-booklog

# src/main/resources/application.yml 파일 오픈API 키 입력

mvn spring-boot:run
```

- visit [http://localhost:8080/](http://localhost:8080/)

---

카카오 오픈API 키 발급 (다음 책 검색에 사용됨)
- https://developers.kakao.com/docs/restapi/search#%EC%B1%85-%EA%B2%80%EC%83%89
- https://developers.kakao.com/apps


구글 오픈API 키 발급
- https://console.developers.google.com/home/dashboard
- https://developers.google.com/books/docs/v1/using
- https://developers.google.com/api-client-library/java/apis/books/v1

키를 받아야 사용하실 수 있습니다.

---

~~다음 오픈API 키 발급~~
~~- http://developers.daum.net/services/apis/search/book~~
~~- http://developers.daum.net/console~~