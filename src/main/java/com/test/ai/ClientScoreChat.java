//// filename: ClientScoreChat.java
//import java.net.URI;
//import java.time.Duration;
//
//public class ClientScoreChat {
//
//    private static final String API_BASE = "http://192.168.10.30:9000";
//    private static final String PASSWORD = "20250816";
//
//    // 简单 JSON 转义
//    private static String j(String s) {
//        return s.replace("\\", "\\\\").replace("\"", "\\\"");
//    }
//
//    public static void main(String[] args) throws Exception {
//        // 在代码中直接写入整段对话（按需替换/扩展）
//        String chatJsonArray =
//                "[" +
//                "{\"role\":\"assistant\",\"content\":\"" + j("Hi! Limited-time offer on Portable Blender.") + "\"}," +
//                "{\"role\":\"user\",\"content\":\"" + j("We go live this week. Please send a tax-included quote and earliest delivery date.") + "\"}," +
//                "{\"role\":\"assistant\",\"content\":\"" + j("Tax-included price is $49.9; delivery in 2 days.") + "\"}," +
//                "{\"role\":\"user\",\"content\":\"" + j("That works for us if you can invoice today.") + "\"}" +
//                "]";
//
//        String body =
//                "{" +
//                "\"password\":\"" + j(PASSWORD) + "\"," +
//                "\"chat\":" + chatJsonArray +
//                "}";
//
//        HttpClient client = HttpClient.newBuilder()
//                .connectTimeout(Duration.ofSeconds(10))
//                .build();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(API_BASE + "/score_chat"))
//                .timeout(Duration.ofSeconds(60))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(body))
//                .build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        int code = response.statusCode();
//        String respBody = response.body();
//
//        if (code != 200) {
//            throw new RuntimeException("HTTP " + code + ": " + respBody);
//        }
//
//        // 这里直接打印 JSON；如需结构化解析，可接入 Jackson/Gson
//        System.out.println(respBody);
//    }
//}
