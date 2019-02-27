package me.chosi.springjsp02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springjsp02Application {

    public static void main(String[] args) {
        SpringApplication.run(Springjsp02Application.class, args);
    }

}
/**

    java -jar를 사용해서 배포하기
        - Springjsp02Application를 사용, 임베디드 톰캣에 디스패처 서블릿을 추가해서 실행하는 것임
        - 스프링 애플리케이션 안에 톰캣이 들어가 있음

 1. -mvnw package

 2. java -jar target/*.war


    서블릿 컨테이너(Tomcat, Jetty ...)에 배포하기
        - ServerInitializer를 사용. war로 배포. 톰캣으로 실행
        - 톰캣 안에 서블릿이 등록되는 것

 1. 톰캣 다운로드

 2. 실행 쪽 아이콘에서 에디트 누르고 톰캣 있는 경로 추가

 3. 밑에 fix에서 둘 중 하나 선택하고 어플라이누르고 확인

 */