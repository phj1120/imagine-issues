## 소개

Spring 스케줄러를 이용해 포스팅 자동화

## 기능

prompt 생성: Instagram Graph API

이미지 생성: Karlo API

이미지 배포: 자체 이미지 서버

게시글 게시: Instagram Graph API

## 활용

### Animai

이전 게시글에 달린 댓글(없으면 자체 생성)을 기반으로 prompt 를 만들고,

이를 이용해 이미지 생성 후 게시

## TODO

### 설계 수정

AS-IS

```
기능들이 서로에 종속적으로 구성 되어있어,
하나의 기능이 바뀐다면, 다른 기능들의 코드도 수정이 필요함
```

TO-BE

```
변경에 용이하도록 리팩터링
ex) 이미지 생성을 karlo 말고 deepai 로 바꿔도 그외의 서비스는 코드를 수정하지 않아도 되도록

interface 를 정의하고, 각각의 구현체를 만드는 것으로 수정할 예정
```

### prompt 수정

AS-IS

```
댓글 및 저장된 기본 값 기반으로 프롬프트 생성
[who], [where], [when], [what], by pixar style
```

TO-BE

```
사람들이 관심 있을 만한 주제 생각해 보기

* 당일 이슈 70년대 펜화 스타일로 1장 요약 
* 한 가지 동물에 특화되어 귀엽게 prompt 나오도록.... 
```
