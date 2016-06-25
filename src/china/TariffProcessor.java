package china;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import common.Tariff;

@SuppressWarnings("unused")
public class TariffProcessor implements PageProcessor
{

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public Site getSite()
    {
        return site;
    }

    @Override
    public void process(Page page)
    {
        List<Selectable> nodes = page.getHtml().xpath("div[@class='scx_item']").nodes();
        for (Selectable node : nodes)
        {
            String xcode = node.xpath("/div/div[1]/div[2]/text()").toString();
            String xname = node.xpath("/div/table[1]//span/text()").toString();
            String xspecific = node.xpath("/div/table[2]//span/text()").toString();
            String xunit1 = node.xpath("/div/div[2]/div[2]/text()").toString();
            String xunit2 = node.xpath("/div/div[2]/div[4]/text()").toString();
            String ximportTariffRate = node.xpath("/div/div[3]/div[4]/text()").toString();
            String ximportVatRate = node.xpath("/div/div[5]/div[2]/text()").toString();
            String xexportTariffRate = node.xpath("/div/div[4]/div[4]/text()").toString();
            String xexportRebateRate = node.xpath("/div/div[4]/div[6]/text()").toString();
            String xcondition = node.xpath("/div/div[5]//label/text()").toString();
            String xdescription = node.xpath("/div/table[3]//span/text()").toString();

            Tariff tariff = new Tariff();
            tariff.setCode(xcode.trim());
            tariff.setName(xname.trim());
            tariff.setCountry("中国");
            tariff.setSpecific(xspecific.trim());
            tariff.setUnit1(xunit1.trim());
            tariff.setUnit2(xunit2.trim());
            tariff.setImportTariffRate(ximportTariffRate.trim());
            tariff.setImportVatRate(ximportVatRate.trim());
            tariff.setExportTariffRate(xexportTariffRate.trim());
            tariff.setExportRebateRate(xexportRebateRate.trim());
            tariff.setCondition(xcondition.trim());
            tariff.setDescription(xdescription.trim());

            page.putField(xcode, tariff);
        }

        String xtotal = page.getHtml().xpath("div[@class='total_info']/text()").regex("\\d+").toString();
        String xcurr = page.getHtml().xpath("li[@class='selected']/text()").toString();

        Integer curr = Integer.valueOf(xcurr.trim());
        Integer total = Integer.valueOf(xtotal.trim());

        if (curr < 30)
        {
            page.addTargetRequest(createRequestUrl(curr + 1));
        }
    }

    private static Request createRequest(Integer page)
    {
        Request request = new Request("http://www.hscode.net/IntegrateQueries/YsInfoPager");
        Map<String, Object> extras = new HashMap<>();
        extras.put("pageIndex", page);
        extras.put("taxCode", "");
        extras.put("productName", "");
        request.setExtras(extras);
        // request.setMethod(HttpConstant.Method.POST);
        return request;
    }

    private static String createRequestUrl(Integer page)
    {
        return String.format("http://www.hscode.net/IntegrateQueries/YsInfoPager?pageIndex=%d", page);
    }

}
