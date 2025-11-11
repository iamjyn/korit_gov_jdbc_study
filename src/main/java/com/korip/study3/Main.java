package com.korip.study3;

/*
 * 게시물
 * id, title, content, username, createDt
 * 제목, 내용, 닉네임(username), 생성일 필수값
 * 제목은 중복 불가
 *
 * 추가
 * 제목, 내용, 닉네임(username) 입력받고 넣기
 * 제목은 중복확인하기
 *
 * id로 단건 조회
 * username으로 게시물 여러개 조회
 * 키워드 검색 조회 => 제목 또는 내용
 *
 * 단, 조회시 가장 최신 게시물부터 정렬
 * */

import com.korip.study3.dto.PostReqDto;
import com.korip.study3.entity.Post;
import com.korip.study3.service.PostService;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PostService postService = PostService.getInstance();

        while (true) {
            System.out.println(" [ 게시글 관리 ] ");
            System.out.println("1. 게시글 작성");
            System.out.println("2. id 조회");
            System.out.println("3. username 조회");
            System.out.println("4. 게시글 검색");
            System.out.println("q. 종료");
            System.out.print("메뉴 입력: ");
            String selectMenu = scanner.nextLine();
            if ("q".equalsIgnoreCase(selectMenu)) {
                System.out.println("프로그램 종료");
                break;
            } else if ("1".equals(selectMenu)) {
                System.out.println("[ 게시글 작성 ]");
                PostReqDto postReqDto = new PostReqDto();
                while (true) {
                    System.out.print("제목 입력: ");
                    postReqDto.setTitle(scanner.nextLine());
                    if (!postService.isValidDuplicatedTitle(postReqDto.getTitle())) {
                        break;
                    }
                    System.out.println("이미 작성된 title입니다.");
                }
                System.out.print("내용 입력: ");
                postReqDto.setContent(scanner.nextLine());
                System.out.print("username 입력: ");
                postReqDto.setUsername(scanner.nextLine());
                int result = postService.addPost(postReqDto);
                if (result == 0) {
                    System.out.println("게시글 작성이 실패하였습니다.");
                }
                System.out.println("게시글 작성이 완료되었습니다.");
            } else if ("2".equals(selectMenu)) {
                System.out.println("[ id 단건 조회 ]");
                System.out.print("id 입력: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                List<Post> postList = Collections.singletonList(postService.searchId(id));
                postList.forEach(System.out::println);
            } else if ("3".equals(selectMenu)) {
                System.out.println("[ username 검색 ]");
                System.out.print("username 입력: ");
                String name = scanner.nextLine();
                List<Post> postList = postService.searchByUsername(name);
                postList.forEach(System.out::println);

            } else if ("4".equals(selectMenu)) {
                System.out.println("[ 게시글 검색 ]");
                System.out.print("키워드 검색: ");
                String keyword = scanner.nextLine();
                List<Post> postList = postService.searchPostKeyword(keyword);
                postList.forEach(System.out::println);
            }
        }
    }
}
