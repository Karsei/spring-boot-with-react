# spring-boot-with-react

Spring Boot + React 를 결합한 프로젝트 구성

## Spec

* Spring Boot 2.6.6 (Gradle 7.4.1)
* React 18 (Node 16+)

## 구성

React 는 `frontend` 폴더에서 관리한다.

`build.gradle` 파일에서 빌드에 대한 처리를 구성해주는데 Gradle 에서 빌드할 때 React 를 먼저 빌드하고, `/src/main/resources/static` 으로 복사한 다음에 마지막으로 Java 컴파일을 진행한다. IDE 에서 Backend 개발 시에도 똑같이 미리 Raect 빌드 후 이루어지기 때문에 상관없다.

Spring 에서는 기본적으로 `static` 폴더에 파일이 존재하면 알아서 웹 페이지를 연결해주기 때문에 따로 구성은 필요없다.

## 참고사항

CORS 대비는 아직 구성되어 있지 않음 

## 사용법

```bash
$ ./gradlew clean build
```