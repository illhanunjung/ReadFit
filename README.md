# :pushpin: ReadFit
> 자연어처리 기반 2030 남성들을 위한 신발 리뷰 분석 플랫폼

</br>

## 1. 제작 기간 & 참여 인원(語시스트)
- 2024년 월3 12일 ~ 4월 15일
- 팀 프로젝트
- 팀장 송세림
- 팀원 이진근
- 팀원 곽진규
- 팀원 임은영
- 팀원 최지호

</br>

## 2. 사용 기술
#### `Back-end`
  - Java 
  - Spring Boot
  - MySQL 
  - Python 
  - Flask  
#### `Front-end`
  - HTML
  - CSS
  - Javascript
  - React
  - Ajax
#### `API`
  - Kakao Login API
  - Naver Clova API
#### `Model`
  - KcElectra
  - KoElectra
  - GPT 3.5 turbo
#### `배포`
  - AWS
#### `버전관리`
  - GIT
</br>

## 3. ERD 설계
![ReadFit](https://github.com/illhanunjung/ReadFit/assets/144203058/59f6baa1-3414-4803-a475-5246160864bd)


## 4. 핵심 기능
- 자연어처리 기반 리뷰 분석
  - 키워드 랭킹
  - 전체키워드 긍/부정분석
  - 키워드별 긍/부정 분석
  - 리뷰 하이라이트
  - 리뷰 긍/부정 분석
- 리뷰 한줄요약
- 대화형 챗봇기능

<details>
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">

### 4.1. 전체 흐름
![시스템 아키텍쳐](https://github.com/illhanunjung/ReadFit/assets/144203058/ea1b12b8-ca1e-46de-ba1a-60820fcdcfe6)
<br>

### 4.2 키워드 랭킹
![image](https://github.com/illhanunjung/ReadFit/assets/144203058/a5a43b60-3735-4314-a872-dc36e8444217)

 <br>
[코드 확인]()
- 가ㅏ가ㅏㄱ
<br>

### 4.3 전체키워드 긍/부정분석
![image](https://github.com/illhanunjung/ReadFit/assets/144203058/04e148d4-fc45-4ffc-b21a-98f592617741)

 <br>
[코드 확인]()
- 가ㅏ가ㅏㄱ
<br>

### 4.4 키워드별 긍/부정 분석
![image](https://github.com/illhanunjung/ReadFit/assets/144203058/36f4d8d9-2123-4134-b6c2-2d682a0d86d5)

 <br>
[코드 확인]()
- 가ㅏ가ㅏㄱ
<br>

### 4.5 리뷰 하이라이트
![image](https://github.com/illhanunjung/ReadFit/assets/144203058/036c888e-21ed-4c5a-a9e9-ab584b432314)

 <br>
[코드 확인]()
- 가ㅏ가ㅏㄱ
<br>

### 4.6 리뷰 긍/부정 분석
<img width="491" alt="image" src="https://github.com/illhanunjung/ReadFit/assets/144203058/f599af79-a1bf-4345-988f-7a73a286c9dd">


 <br>
[코드 확인]()
- 가ㅏ가ㅏㄱ
<br>

### 4.7 리뷰 한줄요약
![image](https://github.com/illhanunjung/ReadFit/assets/144203058/50ccd14f-13c0-48d1-8f92-06ae46dd15d9)
<br>
[코드 확인]()
- 가ㅏ가ㅏㄱ
<br>

### 4.8 대화형 챗봇기능
![image](https://github.com/illhanunjung/ReadFit/assets/144203058/82d21d3e-9373-47b1-854c-0c670906281e)

<br>
[코드 확인]()
- 가ㅏ가ㅏㄱ
<br>

</div>
</details>

</br>
 
## 5. 팀원 소개

<details>
<summary><b>팀장 송세림</b></summary>
<div markdown="1">

#### `Front-End`
 - 관리자 페이지
#### `Back-end`
 - 회원관리 기능
 - 댓글관리 기능
 - 게시글 관리 기능
 - 관리자 로그인 기능
 - 회원가입 기능
 - Kakao login API
  #### `Modeling`
- RAG 구조를 활용한 LLM (gpt3.5)모델 챗봇 개발
- Transformer Kc-ELECTRA 모델 활용 키워드 분석모델 개발
  #### `설계`
 - 시스템 아키텍쳐 
 - 테이블 명세
 - 서비스 흐름도
 - DB구축, 관
 - 데이터 등록
   #### `배포`
 - AWS EC2를 사용하여 배포
   #### `PM`
 - GIT 
 - 일정관리
</div>
</details> 

<details>
<summary><b>팀원 이진근</b></summary>
<div markdown="1">

#### `Front-End`

#### `Back-end`

#### `기타`

</div>
</details>

<details>
<summary><b>팀원 곽진규</b></summary>
<div markdown="1">
  
#### `Front-End`

<details>
<summary><b>팀원 임은영</b></summary>
<div markdown="1">
  
#### `Front-End`
- 프로필 페이지


#### `Back-end`
- 로그인 기능
- 프로필 사진, 개인정보 변경 기능
- 프로필 페이지 관심상품 관리
- 프로필 페이지 관심상품 긍부정
- 메인페이지 관심상품 TOP3 기능

#### `Modeling`
- RAG 구조를 활용한 LLM (gpt3.5)모델 챗봇 개발
- Transformer Kc-ELECTRA 모델 활용 키워드 분석모델 개발
- Transformer Ko-ELECTRA 모델 활용 긍/부정 분석모델 개발

#### `설계`
- 유스케이스
- 빅데이터분석정의서

#### `배포`
- AWS EC2를 사용하여 배포
 
</div>
</details>

#### `Back-end`

</div>
</details>




<details>
<summary><b>팀원 최죠</b></summary>
<div markdown="1">
  
#### `Front-End`


#### `Back-end`

</div>
</details>

