각 역할과 기능에 맞게 MVC패턴을 쓰시면 됩니다.

예시)

A 담당
게임 진행 / 기록
→ GameController
→ 필요한 DTO/DAO 작성

B 담당
홀 / 손님
→ HallController
→ 필요한 DTO/DAO 작성

C 담당
주방 / 요리
→ KitchenController
→ 필요한 DTO/DAO 작성

D 담당
재고 / 발주
→ ProductController
→ 필요한 DTO/DAO 작성

----DAO 파일 구조----

GameController
 ├─ GameStateDAO
 └─ GameLogDAO

HallController
 ├─ CustomerDAO
 ├─ CookDAO
 ├─ MenuDAO
 └─ GameStateDAO

KitchenController
 ├─ RecipeDAO
 ├─ MenuDAO
 ├─ ProductDAO
 └─ CookDAO

ProductController
 ├─ ProductDAO
 └─ ProductLogDAO


---- View ----
view의 경우
홀의 화면에는 게임진행/기록, 홀/손님 담당들이 하나의 화면에 쓰니까
HallGameView식으로 하고, 테스트하기 편하게 HallGameView1, HallGameView2로 쓴다음 취합 생각중입니다

주방의 화면에서는 주방/요리, 재고/발주 담당들이 하나의 화면에 쓰니까
KitchenProductView식으로 하고, 테스트하기 편하게 KitchenProductView1, KitchenProductView2로 쓴 다음 취합하는 방향입니다.