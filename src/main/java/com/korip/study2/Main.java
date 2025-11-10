package com.korip.study2;

import com.korip.study2.dto.SigninReqDto;
import com.korip.study2.dto.SignupReqDto;
import com.korip.study2.entity.User;
import com.korip.study2.service.UserService;
import com.korip.study2.util.PasswordEncoder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = UserService.getInstance();

        while (true) {
            System.out.println(" [ 회원관리 ] ");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 전체회원 조회");
            System.out.println("4. 회원 검색");
            System.out.println("q. 종료");
            System.out.print("메뉴를 선택하세요: ");
            String selectMenu = scanner.nextLine();
            if ("q".equalsIgnoreCase(selectMenu)) {
                System.out.println("프로그램 종료");
                break;
            } else if ("1".equals(selectMenu)) {
                System.out.println("[ 회원가입 ]");
                SignupReqDto signupReqDto = new SignupReqDto();
                while (true) {
                    System.out.print("username: ");
                    signupReqDto.setUsername(scanner.nextLine());
                    if (!userService.isValidDuplicatedUsername(signupReqDto.getUsername())) {
                        break;
                    }
                    System.out.println("이미 사용 중인 username입니다.");
                }
                while (true) {
                    System.out.print("password: ");
                    signupReqDto.setPassword(scanner.nextLine());
                    if (!signupReqDto.getPassword().isBlank()) {
                        break;
                    }
                    System.out.println("비밀번호는 공백일 수 없습니다. 다시 입력하세요.");
                }
                while (true) {
                    System.out.print("email: ");
                    signupReqDto.setEmail(scanner.nextLine());
                    if (!userService.isValidDuplicatedEmail(signupReqDto.getEmail())) {
                        break;
                    }
                    System.out.println("이미 사용 중인 email입니다.");
                }
                // todo: 회원가입 메소드 호출
                int result = userService.signup(signupReqDto);
                if (result == 0) {
                    System.out.println("회원 가입에 실패하였습니다.");
                }
                System.out.println("회원 가입이 완료되었습니다.");
            } else if ("2".equals(selectMenu)) {
                System.out.println("[ 로그인 ]");
                SigninReqDto signinReqDto = new SigninReqDto();
                System.out.print("username: ");
                signinReqDto.setUsername(scanner.nextLine());
                System.out.print("password: ");
                signinReqDto.setPassword(scanner.nextLine());
                // todo: 로그인 메소드 호출
                User user = userService.signin(signinReqDto);
                if (user != null) {
                    System.out.println("로그인 되었습니다.");
                    System.out.println("로그인 된 유저: " + user);
                }
            } else if ("3".equals(selectMenu)) {
                System.out.println("[ 전체회원 조회 ]");
                userService.printAllUser().forEach(System.out::println);
                // todo: 전체회원 조회 메소드 호출

            } else if ("4".equals(selectMenu)) {
                System.out.println("[ 회원 검색 ]");
                System.out.print("회원명: ");
                userService.searchUser(scanner.nextLine());
                break;
                // todo: 회원 검색 메소드 호출
            }
        }

    }
}
