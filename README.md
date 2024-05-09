### 배포 주소

[Qudditch](https://qudditch.dawoony.com)

### 💡 프로젝트 소개

- 무인 편의점 관리 시스템 및 무인 편의점 이용 앱

#### 주요기능

1. 재고관리
2. 실시간 통계 대시보드
3. 발추 추천 시스템
4. 무인매장 출입
5. 챗봇을 이용한 매장/재고 검색
6. 대기시간없는 편리한 쇼핑

### 💡 스택

#### 공통

![cf](https://img.shields.io/badge/Cloudflare-F38020?style=for-the-badge&logo=Cloudflare&logoColor=white)
![jenkins](https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white)
![git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)
![fig](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)
![Discord](https://img.shields.io/badge/Discord-%235865F2.svg?style=for-the-badge&logo=discord&logoColor=white)

#### 백엔드

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![springboot](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![ms](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![red](https://img.shields.io/badge/redis-%23DD0031.svg?&style=for-the-badge&logo=redis&logoColor=white)
![spring-security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)
![Naver Clova](https://img.shields.io/badge/NAVER_CLOVA-%46E3B7.svg?style=for-the-badge&logoColor=white)

#### 프론트

![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![next](https://img.shields.io/badge/Next.js-000?logo=nextdotjs&logoColor=fff&style=for-the-badge)
![prettier](https://img.shields.io/badge/prettier-1A2C34?style=for-the-badge&logo=prettier&logoColor=F7BA3E)
![Vercel](https://img.shields.io/badge/vercel-%23000000.svg?style=for-the-badge&logo=vercel&logoColor=white)

### 💡 순서도

- 고객
  <img src="https://github.com/SSG-Golden-Snitch/.github/assets/149459170/3fbd5413-5a0e-441c-a018-73f681fa96fd" alt="점주"/>

- 점주
  <img src="https://github.com/SSG-Golden-Snitch/.github/assets/149459170/d59c62e6-63a6-47a9-97ec-ea223a22a942" alt="점주"/>

- 관리자
  <img src="https://github.com/SSG-Golden-Snitch/.github/assets/149459170/915affe0-f9b9-49e1-aff8-84e922374a7c" alt="관리자"/>

### 💡 서버 아키텍쳐

<img src="https://github.com/SSG-Golden-Snitch/.github/assets/149459170/5a6a0fc3-d1ac-4d58-850b-cee3b4cdac8c" width='500px' alt="서버 아키텍쳐"/>

### 💡 화면 구성 (주요)

#### 점주

##### 대시보드
![image](https://github.com/SSG-Golden-Snitch/.github/assets/35947660/eb998985-76a1-4f4e-914f-6fb21166ed45)

##### 재고관리
![image](https://github.com/SSG-Golden-Snitch/.github/assets/35947660/4ea1a5f4-a23a-46a0-9ca9-9e1f22928df5)

##### 발주 추천
![image](https://github.com/SSG-Golden-Snitch/.github/assets/35947660/86e6cc24-92a0-4848-a81f-9e45288b1652)

#### 고객

##### 무인 매장 출입
![image](https://github.com/SSG-Golden-Snitch/.github/assets/35947660/e261bb6a-1ef4-4fc7-9ee6-08bd9e095eb4)

##### 챗봇을 이용한 매장/제품 검색
<img src="https://github.com/SSG-Golden-Snitch/.github/assets/35947660/ed65c61f-5250-4d06-aaf1-dc0e1534d283" height="700px" alt="챗봇을 이용한 매장/제품 검색" />

##### 결제
![image](https://github.com/SSG-Golden-Snitch/.github/assets/35947660/3d7f6ad2-a8df-4304-855d-1686533e81a0)

### 💡 트러블 슈팅

#### 안면인식 지연시간
1. Kinesis 비디오 스트림의 소비자로 rekognition 사용 \
**지연시간 10s~30s**
2. Kinesis Signal Chanel로 비디오 스트림 연결 방법 변경 \
**지연시간 10s~30s**
3. 실시간 스트리밍을 사용하지 않고 MediaPipe를 이용하여 클라이언트에서 안면을 인식했을 때 이미지만 요청하여 비교 \
**지연시간 1.3s~1.7s**

#### PWA 팝업 알림
- 안드로이드 브라우저에서 알림 팝업이 되지 않는 현상 발생
- 아이폰의 사파리 브라우저에서는 팝업에 제대로 표시됨을 확인
- PWA라고 해도 OS와 브라우저 환경에 따라 다르게 동작함 이해 

#### Clova Chatbot
- 챗봇에서 위치기반 서비스를 이용하기 위해 사용자의 위치 정보를 받아와야 하는 문제 발생.
- 사용자가 위치 정보를 포함한 메시지를 보낼 때 정규표현식으로 패턴을 설정하여 위치 정보로 DB에 쿼리가 필요함 구분.
- 받은 위치 정보와 함께 DB에 쿼리를 날려 결과를 전달하는 방식으로 챗봇을 업데이트하여 문제를 해결.
