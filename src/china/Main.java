package china;

import java.util.HashMap;
import java.util.Map;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

@SuppressWarnings("unused")
public class Main
{

    public static void main(String[] args)
    {
        Spider spider = Spider.create(new TariffProcessor());
        spider.addPipeline(new TariffPipeline());
        // spider.addRequest(createStartRequest());
        spider.addUrl(createStartRequestUrl());
        spider.thread(10).start();
    }

    private static Request createStartRequest()
    {
        Request request = new Request("http://www.hscode.net/IntegrateQueries/YsInfoPager");
        Map<String, Object> extras = new HashMap<>();
        extras.put("pageIndex", 3);
        extras.put("taxCode", "");
        extras.put("productName", "");
        request.setExtras(extras);
        // request.setMethod(HttpConstant.Method.POST);
        return request;
    }

    private static String createStartRequestUrl()
    {
        return "http://www.hscode.net/IntegrateQueries/YsInfoPager?pageIndex=1";
    }

}
