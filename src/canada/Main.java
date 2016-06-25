package canada;

import us.codecraft.webmagic.Spider;
import china.TariffPipeline;
import china.TariffProcessor;

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

    private static String createStartRequestUrl()
    {
        return "http://www.cbsa-asfc.gc.ca/trade-commerce/tariff-tarif/2015/html/tblmod-2-eng.html";
    }
}
