package canada;

import java.util.List;
import java.util.regex.Pattern;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import common.Tariff;

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
        List<Selectable> nodes = page.getHtml().xpath("table/tbody/tr").nodes();
        for (Selectable node : nodes)
        {
            String xitem = node.xpath("/tr/td[1]/text()").toString();
            String xss = node.xpath("/tr/td[2]/text()").regex("\\d+").toString();

            if (xitem == null || xss == null)
            {
                continue;
            }

            String xcode = xitem.replace(".", "").trim() + xss.trim();
            String xname = node.xpath("/tr/td[3]/text()").toString();
            String xspecific = "";
            String xunit1 = node.xpath("/tr/td[4]/text()").toString();
            String xunit2 = "";
            String ximportTariffRate = node.xpath("/tr/td[5]/text()").regex("[\\d\\.]+").toString();
            String ximportVatRate = "";
            String xexportTariffRate = "";
            String xexportRebateRate = "";
            String xcondition = node.xpath("/tr/td[6]/text()").toString();
            String xdescription = xname;

            if (xname == null)
            {
                xname = "";
            }

            if (xunit1 == null || xunit1.length() == 1)
            {
                xunit1 = "";
            }

            if (ximportTariffRate == null)
            {
                ximportTariffRate = "0";
            }

            ximportTariffRate = ximportTariffRate + "%";

            if (xcondition == null || xcondition.length() == 1)
            {
                xcondition = "";
            }

            if (xdescription == null)
            {
                xdescription = "";
            }

            Tariff tariff = new Tariff();
            tariff.setCode(xcode.trim());
            tariff.setName(xname.trim());
            tariff.setCountry("加拿大");
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
    }

    public static void main(String[] args)
    {
        Pattern regex = Pattern.compile("");

        String str = "1.9�/kg";
        System.out.println(str.replaceAll("(^\\w*)|(\\w*$)", ""));
        System.out.println(str.replaceAll("\\w+$", ""));
    }

}
