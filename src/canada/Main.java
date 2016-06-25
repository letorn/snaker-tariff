package canada;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.selector.Html;

public class Main
{

    public static void main(String[] args)
    {
        Spider spider = Spider.create(new TariffProcessor());
        spider.addPipeline(new TariffPipeline());
        spider.addUrl(createRequestUrls().toArray(new String[0]));
        // spider.addUrl("http://www.cbsa-asfc.gc.ca/trade-commerce/tariff-tarif/2015/html/00/ch01-eng.html");
        // spider.addUrl("http://www.cbsa-asfc.gc.ca/trade-commerce/tariff-tarif/2015/html/02/ch98-eng.html");
        spider.thread(10).start();
    }

    private static List<String> createRequestUrls()
    {
        List<String> urls = new ArrayList<>();
        HttpClientDownloader http = new HttpClientDownloader();
        Html html = http.download(createStartRequestUrl());

        List<String> links = html.xpath("tr/td[@class='align-center']/a/@href").all();
        for (String link : links)
        {
            if (link.matches(".*ch(\\d+)-eng.html"))
            {
                urls.add(link);
            }
        }

        return urls;
    }

    private static String createStartRequestUrl()
    {
        return "http://www.cbsa-asfc.gc.ca/trade-commerce/tariff-tarif/2015/html/tblmod-2-eng.html";
    }
}
