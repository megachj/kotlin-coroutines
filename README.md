# kotlin-coroutines
코틀린 코루틴

## jdk19 개발 환경
인텔리제이 SDK 와 Gradle 설정을 해야한다.
* SDK 설정: Project Structure > SDK > jdk19 설정
* Gradle 설정: Settings > Build, Execution, Deployment > Build Tools > Gradle > General Settings > Gradle user home 을 다운받은 gradle 8.5 디렉토리를 설정  
예) /usr/local/Cellar/gradle/8.5

가상 스레드 사용하려면 jdk19 에서는 실행 시에 VM options '--enable-preview' 를 설정해야한다.
