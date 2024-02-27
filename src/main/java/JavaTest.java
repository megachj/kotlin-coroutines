import bookdeepdive.chap28.AnkiConnector;
import bookdeepdive.chap28.p01.AnkiConnectorBlocking;
import bookdeepdive.chap28.p02.AnkiConnectorCallback;
import bookdeepdive.chap28.p03.AnkiConnectorFuture;
import bookdeepdive.chap28.p04.AnkiConnectorReactor;
import kotlin.Result;

import java.util.concurrent.CompletableFuture;

public class JavaTest {
    public static void main(String[] args) throws Exception {
        중단함수_테스트();
        System.out.println();

        System.out.println("---------- 블로킹함수 테스트");
        블로킹함수_테스트();
        System.out.println();

        // 콜백함수

        System.out.println("---------- 퓨처함수 테스트");
        퓨처함수_테스트();
        System.out.println();

        System.out.println("---------- 리액터함수 테스트");
        리액터함수_테스트();
        System.out.println();
    }

    private static void 중단함수_테스트() {
        AnkiConnector connector = new AnkiConnector();
        // 중단 함수는 리턴타입이 Object(Any?) 로 바뀌고, 파라미터에 Continuation 이 생김
        // boolean connection = connector.checkConnection(); // 컴파일 에러
    }

    private static void 블로킹함수_테스트() {
        AnkiConnectorBlocking connectorBlocking = new AnkiConnectorBlocking();
        boolean connection = connectorBlocking.checkConnection();
        System.out.printf("[%s] 결과: %s%n", Thread.currentThread().getName(), connection);
    }

    private static void 콜백함수_테스트() {
        AnkiConnectorCallback connectorCallback = new AnkiConnectorCallback();
//        connectorCallback.checkConnection(new ConnectionCallback() {
//            @Override
//            public void invoke(Result<Boolean> result) {
//
//            }
//        });
    }

    private static void 퓨처함수_테스트() throws Exception {
        AnkiConnectorFuture connectorFuture = new AnkiConnectorFuture();
        CompletableFuture<Boolean> future = connectorFuture.checkConnection();
        System.out.printf("[%s] 결과: %s%n", Thread.currentThread().getName(), future.get());
    }

    private static void 리액터함수_테스트() {
        AnkiConnectorReactor connectorReactor = new AnkiConnectorReactor();
        boolean connection = connectorReactor.checkConnection().block();
        System.out.printf("[%s] 결과: %s%n", Thread.currentThread().getName(), connection);
    }

    @FunctionalInterface
    interface ConnectionCallback {
        void invoke(Result<Boolean> result);
    }
}
