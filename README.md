# Smart-door-guard Backend

스마트 문 침입 방지 시스템의 Backend 입니다.
디바이스(ESP32)로부터 올라오는 이벤트를 받아 침입 여부를 판단하고, 상태 머신을 관리하며, 앱으로의 실시간 알림과 SMS 발송을 담당합니다.
서비스 이용 중 문의사항이 있으시거나 개선 의견이 있으신 경우 언제든 환영합니다. 프로젝트에 참여하고 싶으시다면 issue 혹은 pull request를 생성해주세요!

## 주요 기능

- **인증/장치**: JWT 기반 회원 인증, 일회성 페어링 토큰 발급, MQTT LWT를 통한 장치 온라인/오프라인 추적
- **개폐 제어**: 앱 요청을 MQTT로 중계, ACK/타임아웃 처리, 문틈 물체(ToF 거리 이상) 감지 시 결박 명령 거부
- **침입 대응**: 연속 충격 + 문 열림 조건 기반 오탐 필터링, SMS 발송, 60초 디바운스, 침입 확정 시 자동 결박 연동
- **카메라/AI**: 디바이스 프레임 중계, AI 추론 모듈 연동(사람 판정), 스냅샷 및 Bounding Box 메타데이터 저장
- **상태 관리**: `NORMAL` → `WATCH` → `WARNING` → `INTRUSION` 상태 머신 관리 및 WebSocket 실시간 전송

## Tech Stack

- Java 21, Spring Boot
- Spring Data JPA, MySQL
- Spring Security, JWT
- WebSocket(실시간 상태 구독), MQTT(장치 ↔ 서버 통신)
- (연동) SMS 게이트웨이, FCM, AI 추론 모듈

## Architecture

도메인 중심 레이어드 패턴(`controller → service → repository → entity`)을 따릅니다.
Backend가 시스템 상태 머신의 소유권을 가지며, Frontend와 Firmware가 이를 실시간으로 구독합니다.

| 상태 | 진입 조건 | 동작 |
| --- | --- | --- |
| `NORMAL` | 이상 없음 | 대기 |
| `WATCH` | 카메라 사람 감지 | 스냅샷 저장, 푸시 알림 |
| `WARNING` | 사람 감지 + 문 열림 | 푸시 알림 + 부저 |
| `INTRUSION` | 충격 임계 초과(필터 통과) | 강철 암 결박 + SMS + 푸시 + 부저 |

## Getting Started

### 요구 사항

- JDK 21
- MySQL
- MQTT Broker
- (선택) SMS 게이트웨이, FCM, AI 추론 서버 — 해당 기능 사용 시에만 필요

### 실행

```bash
# .env 준비 (.env.example 참고)
cp .env.example .env

# 빌드
./gradlew clean build

# 로컬 실행
./gradlew bootRun

# 테스트
./gradlew test
```

## API 문서

주요 REST API 요약입니다.

| 메서드 | 경로 | 설명 |
| --- | --- | --- |
| `POST` | `/auth/login` | 로그인 · 토큰 발급 |
| `POST` | `/devices/pair` | 장치 페어링 |
| `POST` | `/devices/{id}/lock` | 결박/해제 명령 |
| `PATCH` | `/devices/{id}/angle` | 개방 각도 설정 |
| `GET` | `/devices/{id}/state` | 현재 상태 조회 |
| `GET` | `/events?deviceId=` | 이벤트 로그 및 BBox 좌표 조회 |
| `WS` | `/ws/state` | 실시간 상태 구독 |

### AI 모듈 연동 (Backend → AI)

- **Endpoint**: `POST /api/v1/ai/detect`
- **Request**: `device_id`, `timestamp`, `image_data`(Base64)
- **Response**: `is_person`, `confidence`, `bbox`, `inference_time_ms`

### MQTT 토픽 (장치 ↔ 서버)

| 토픽 | 방향 | 페이로드 예시 |
| --- | --- | --- |
| `sg/{id}/cmd` | 서버 → 장치 | `{"action": "lock", "angle": 30}` |
| `sg/{id}/state` | 장치 → 서버 | `{"lock": "locked", "door": "open"}` |
| `sg/{id}/event` | 장치 → 서버 | `{"type": "intrusion", "shock": 2.4}` |
| `sg/{id}/ack` | 장치 → 서버 | `{"cmdId": "102", "result": "ok"}` |
