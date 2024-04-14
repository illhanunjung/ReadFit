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
[코드 확인](https://github.com/illhanunjung/ReadFit-frontend/blob/main/src/components/PieChart.js)
- 데이터 처리
  - 제공된 data에서 keywordName을 labels로, totalCount를 dataValues로 추출합니다.
  - 모든 키워드의 총계를 계산하여 차트에서 퍼센트 값을 계산합니다.
- 차트 구성
  - labels와 데이터 세트를 포함하는 chartData 객체를 설정합니다. 데이터 세트에는 키워드 수와 시각적 차별화를 위한 미리 정의된 배경색이 포함됩니다.
  - 파이 차트에 대한 options을 정의하는데, 여기에는 다음이 포함됩니다:
  - 반응형 및 종횡비 설정을 위한 구성.
  - 데이터 레이블을 스타일링하고 포맷팅하기 위한 datalabels 플러그인 설정. 레이블은 포매터 함수를 사용하여 키워드 이름과 그것의 총계 대비 백분율을 보여줍니다.
  - 레이블 상자의 모양과 위치를 조정하는 범례 설정.
- 렌더링
  - UI 구조화를 개선하기 위해 Card 컴포넌트 안에 Pie 차트를 렌더링합니다. 레이아웃을 반응형으로 관리하기 위해 React Bootstrap의 Container, Row, Col을 사용합니다.
  - 차트의 크기는 일관성을 유지하기 위해 명시적으로 설정됩니다.
<br>

### 4.3 전체키워드 긍/부정분석
![image](https://github.com/illhanunjung/ReadFit/assets/144203058/04e148d4-fc45-4ffc-b21a-98f592617741)

 <br>
[코드 확인](https://github.com/illhanunjung/ReadFit-frontend/blob/main/src/pages/Rboard.js)
- 업로드 예정
<br>

### 4.4 키워드별 긍/부정 분석
![image](https://github.com/illhanunjung/ReadFit/assets/144203058/36f4d8d9-2123-4134-b6c2-2d682a0d86d5)
<br>
[코드 확인](https://github.com/illhanunjung/ReadFit-frontend/blob/main/src/pages/Rboard.js)
- 업로드 예정
<br>

### 4.5 리뷰 하이라이트
![image](https://github.com/illhanunjung/ReadFit/assets/144203058/036c888e-21ed-4c5a-a9e9-ab584b432314)
<br>
[코드 확인]()
- 업로드 예정
<br>

### 4.6 리뷰 긍/부정 분석
<img width="491" alt="image" src="https://github.com/illhanunjung/ReadFit/assets/144203058/f599af79-a1bf-4345-988f-7a73a286c9dd">
<br>
[코드 확인]()
- 업로드 예정
<br>

### 4.7 리뷰 한줄요약
![image](https://github.com/illhanunjung/ReadFit/assets/144203058/50ccd14f-13c0-48d1-8f92-06ae46dd15d9)
<br>
[코드 확인](https://github.com/illhanunjung/ReadFit-frontend/blob/main/src/components/ReviewCard.js)
- 인코딩 설정
  -  파이썬 스크립트의 인코딩을 UTF-8로 설정하여 다양한 문자를 올바르게 처리할 수 있습니다.
- 모듈 임포트와 API 설정
  - sys 모듈을 사용하여 표준 출력의 인코딩을 재설정합니다.
  - PyKakao에서 KoGPT를 임포트하고, 인증 키를 사용하여 API 인스턴스를 초기화합니다.
- 입력 처리
  - 사용자의 입력을 sys.argv[1]을 통해 받습니다. 이는 커맨드 라인에서 스크립트를 실행할 때 제공된 첫 번째 인자입니다.
  - 입력받은 리뷰에서 특수 문자('#'과 줄바꿈)를 제거합니다.
- GPT 요청 생성
  - 수정된 리뷰 텍스트를 포함하는 요약 요청을 생성합니다.
  - prompt 변수를 사용하여 KoGPT에 전달할 질의를 설정합니다.
- API 호출 및 결과 출력:
  - KoGPT의 generate 함수를 호출하여 생성된 요약을 가져옵니다.
  - max_tokens와 top_p를 설정하여 생성 결과의 길이와 생성될 토큰들의 확률을 조절합니다.
  - 결과를 콘솔에 출력합니다.
<br>

### 4.8 대화형 챗봇기능
![image](https://github.com/illhanunjung/ReadFit/assets/144203058/82d21d3e-9373-47b1-854c-0c670906281e)
<br>
[코드 확인](https://github.com/illhanunjung/ReadFit-frontend/blob/main/src/server/app.py)
- Flask App 설정: CORS를 활성화하고, Flask 애플리케이션을 구성합니다.
- MySQL 데이터베이스 상호작용: 사용자 질문에 따라 적절한 SQL 쿼리를 생성하고 실행합니다. 쿼리 결과는 JSON 형태로 클라이언트에 전송됩니다.
- LangChain 및 RetrievalQA: 질문을 처리하고 적합한 SQL 쿼리를 생성하기 위해 LangChain과 RetrievalQA 체인을 사용하여 사용자의 자연어 질문을 SQL 쿼리로 변환하는 과정을 자동화합니다.
- ChatBot: react-simple-chatbot 라이브러리를 사용하여 사용자 입력을 받고, 다양한 스텝을 통해 질문과 답변을 처리합니다.
- SaveUserInput: 사용자의 모든 입력과 봇의 대답을 저장하는 기능을 수행합니다. 입력은 로컬 스토리지의 mem_id와 세션 번호를 사용하여 서버에 전송됩니다.
- DisplayResults: 사용자의 질문에 대한 응답을 처리하고, 응답 데이터에 따라 결과를 보여주는 컴포넌트입니다.
- ConversationPage: 대화 페이지 전체를 구성하며, 대화의 세부 사항을 관리하고 대화를 초기화하는 기능을 포함합니다.
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
- 피그마 및 리액트를 활용한 반응형 웹
- 웰컴 페이지 구현
- 메인 페이지 구현
- 카테고리 페이지 구현
- 카테고리 상세 페이지 구현
- 커뮤니티 페이지 구현
- 커뮤니티 글 작성 페이지 구현
- 커뮤니티 상세페이지 구현 
- 로그인 페이지 구현
- 챗봇 페이지  구현
- 챗봇 대화리스트 페이지 구현
- 회원가입 페이지 구현


#### `Back-end`
- 메인페이지 카테고리별 TOP10 기능 구현
- 메인 페이지 카테고리별 키워드 랭킹 PIE-CHART 기능 구현 
- 스프링 부트  MYSQL DB연동
- 스프링 부트 리액트 연동
- 카테고리페이지 1차,2차카테고리 클릭시 클릭한 카테고리별 정보표시 
- 카테고리 검색기능 구현
- 카테고리 관심상품 기능 구현
- 카테고리 테이블 기능 구현
- 커뮤니티 기능 구현
- 챗봇 대화 데이터 저장 및 대화 데이터 불러오기 기능 구현


</div>
</details>

<details>
<summary><b>팀원 곽진규</b></summary>
<div markdown="1">
  
#### `Front-End`

#### `Back-end`

</div>
</details>

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


<details>
<summary><b>팀원 최죠</b></summary>
<div markdown="1">
  
#### `Front-End`


#### `Back-end`

</div>
</details>

