# 🧠 AI Answers: LLM 기반 문제 풀이 & 학습 플랫폼

[![Version](https://img.shields.io/badge/version-0.0.1--SNAPSHOT-blue)](https://your-project-releases)

> AI Answers는 사용자가 직접 문제를 만들고, 풀고, AI의 도움을 받아 학습할 수 있는 플랫폼입니다.  LLM (Large Language Model, Gemini 2.0 Flash 사용)을 활용하여 힌트, 상세 풀이, 사용자 답변 평가, 맞춤형 문제 생성 등 혁신적인 학습 경험을 제공합니다.

## ✨ 주요 기능

1.  **메인 페이지:** 웹 사이트에 대한 간략한 소개.

    <img src="https://github.com/user-attachments/assets/51566145-3702-43e8-aa56-8fd3d4f97cc4" alt="Main Page Screenshot" style="border: 1px solid black; padding: 5px; margin: 10px; display: block; max-width: 100%; height: auto;">
    <br><br>

2.  **회원가입:** 아이디 중복 체크, 비밀번호/이메일 유효성 검사 후 가입.

    <img src="https://github.com/user-attachments/assets/f7d1c5ae-6bff-40e9-8e2a-4026170a10b5" alt="Signup Screenshot" style="border: 1px solid black; padding: 5px; margin: 10px; display: block; max-width: 100%; height: auto;">
    <br><br>

3.  **로그인:** Spring Security를 이용한 세션 기반 로그인.

    <img src="https://github.com/user-attachments/assets/bc1e892f-4682-49bb-819f-bf73953b3dcb" alt="Login Screenshot" style="border: 1px solid black; padding: 5px; margin: 10px; display: block; max-width: 100%; height: auto;">
    <br><br>

4.  **로그인 후 메인화면 (랜덤 문제):**  사용자가 입력한 문제 중 랜덤으로 하나를 표시.  AI에게 질문할 수 있는 프롬프트 창 제공.

    <img src="https://github.com/user-attachments/assets/94e94218-9898-4bc8-b886-3b9ce86e6c9e" alt="Main Page After Login Screenshot" style="border: 1px solid black; padding: 5px; margin: 10px; display: block; max-width: 100%; height: auto;">
    <br><br>

5.  **힌트 보기:**  LLM을 이용해 문제에 대한 힌트 제공.

    <img src="https://github.com/user-attachments/assets/164d0673-071f-4e0d-852c-6de258495a19" alt="Hint Screenshot" style="border: 1px solid black; padding: 5px; margin: 10px; display: block; max-width: 100%; height: auto;">
    <br><br>

6.  **풀이 보기:** LLM을 이용한 상세 풀이 제공 (정답, 풀이, 분석, 오답 이유, 용어 해설, 핵심 사항, 관련 파트 등).

    <img src="https://github.com/user-attachments/assets/b0a16a93-0d4e-47e7-bc24-113e5c8fbb07" alt="Solution Screenshot" style="border: 1px solid black; padding: 5px; margin: 10px; display: block; max-width: 100%; height: auto;">
    <br><br>

7.  **사용자 답변 해설:**  LLM이 사용자 답변을 평가하고 보완점 제시.

    <img src="https://github.com/user-attachments/assets/aeb64528-b596-4678-a4a9-98692b2a43ff" alt="Answer Feedback Screenshot" style="border: 1px solid black; padding: 5px; margin: 10px; display: block; max-width: 100%; height: auto;">
    <br><br>

8.  **문제 탭:**  문제집 선택 후 해당 문제만 표시.

    <img src="https://github.com/user-attachments/assets/9588225f-a63c-49dc-91ec-4fd3ab604ed2" alt="Problems Tab Screenshot" style="border: 1px solid black; padding: 5px; margin: 10px; display: block; max-width: 100%; height: auto;">
    <br><br>

9.  **문제집 (카테고리):** 문제집 생성/삭제 (사용자가 직접 문제 추가/삭제 가능).

    <img src="https://github.com/user-attachments/assets/1fc80b42-c63d-4fe6-8f88-6fb6dd5b06a0" alt="Problem Set Management Screenshot" style="border: 1px solid black; padding: 5px; margin: 10px; display: block; max-width: 100%; height: auto;">
    <br><br>

10. **문제 입력:** 카테고리(문제집) 선택 후 사용자 문제 입력.

    <img src="https://github.com/user-attachments/assets/7117ebcd-25d7-4932-ae88-9886bc96284a" alt="Problem Input Screenshot" style="border: 1px solid black; padding: 5px; margin: 10px; display: block; max-width: 100%; height: auto;">
    <br><br>

11. **모바일 반응형:**

    <table>
      <tr>
        <td><img src="https://github.com/user-attachments/assets/e3357d87-85b4-41bf-99f8-81d701f2c47a" alt="Mobile Screenshot 1" style="border: 1px solid black; padding: 5px; margin: 5px; display: block; width: 150px;"></td>
        <td><img src="https://github.com/user-attachments/assets/3a720ef2-e9d8-4d01-8560-5830c811c7d7" alt="Mobile Screenshot 2" style="border: 1px solid black; padding: 5px; margin: 5px; display: block; width: 150px;"></td>
        <td><img src="https://github.com/user-attachments/assets/1ea67d7e-5044-416e-91ba-745872f9134f" alt="Mobile Screenshot 5" style="border: 1px solid black; padding: 5px; margin: 5px; display: block; width: 150px;"></td>
      </tr>
      <tr>
        <td><img src="https://github.com/user-attachments/assets/b89aed3e-0ba0-4270-8a96-b72716e40bdd" alt="Mobile Screenshot 3" style="border: 1px solid black; padding: 5px; margin: 5px; display: block; width: 150px;"></td>
        <td><img src="https://github.com/user-attachments/assets/1cd57556-2fbe-4de4-8ab3-801cb619aaf0" alt="Mobile Screenshot 4" style="border: 1px solid black; padding: 5px; margin: 5px; display: block; width: 150px;"></td>
        <td><img src="https://github.com/user-attachments/assets/ba7a1440-b7bf-4373-9764-223df7d315b9" alt="Mobile Screenshot 6" style="border: 1px solid black; padding: 5px; margin: 5px; display: block; width: 150px;"></td>
      </tr>
    </table>
    <br><br>

## 🚀 개발 예정 기능

1.  **이해도 평가 및 분류:** 힌트, 답변 등을 바탕으로 문제 이해도를 평가하여 분류.
2.  **오답 노트:** 사용자가 직접 문제를 오답 노트에 추가/관리.
3.  **맞춤형 모의고사:** 이해도가 낮은 문제나 오답 노트 문제를 기반으로 LLM이 유사 문제 생성, 모의고사 형태로 제공.

## 🛠️ 사용 기술

*   **Language:** Java 17
*   **Framework:** Spring Boot 3.3.2
*   **Build Tool:** Gradle
*   **Database:** MariaDB
*   **ORM:** MyBatis 3.0.3
*   **View Engine:** Thymeleaf
*   **Security:** Spring Security
*   **LLM:** Gemini 2.0 Flash
*   **UI Framework:** Bootstrap

## 💡 프로젝트 회고

이번 개인 프로젝트는 LLM을 활용하여 교육 플랫폼을 구축하는 흥미로운 도전이었습니다.  AI, 특히 LLM이 교육 분야에서 지닌 잠재력을 직접 확인하고, 이를 실제 서비스로 구현하는 과정에서 많은 것을 배울 수 있었습니다.
처음에는 Java와 Spring Boot 환경에서 LLM을 연동하는 것이 낯설게 느껴지기도 했지만, Gemini 2.0 Flash 모델을 연동하고, 힌트, 풀이, 사용자 답변 평가 등 핵심 기능을 구현하면서 점차 자신감을 얻게 되었습니다.  물론, 기술적인 어려움도 있었고, Thymeleaf를 사용한 UI/UX 측면에서는 아쉬움도 남습니다.
하지만, 이 프로젝트를 통해 초보 개발자로서 한 단계 더 성장할 수 있었고, LLM 기반 서비스 개발에 대한 실질적인 경험을 쌓을 수 있었다는 점에서 큰 의미를 부여하고 싶습니다.  특히, 전통적으로 AI와 거리가 있다고 여겨졌던 Java/Spring Boot 환경에서도 LLM을 충분히 활용할 수 있다는 것을 알게 되었습니다.
향후에는 React와 같은 프론트엔드 프레임워크를 학습하여 사용자 인터페이스를 개선하고, 더욱 직관적이고 편리한 사용자 경험을 제공하는 프로젝트를 진행해 보고 싶습니다.
