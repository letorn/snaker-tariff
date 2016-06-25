package canada;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import common.ExcelKit;
import common.Tariff;

public class TariffPipeline implements Pipeline, Closeable
{
    private static List<Tariff> tariffs = new ArrayList<>();

    @Override
    public void process(ResultItems resultItems, Task task)
    {
        Map<String, Object> resultMap = resultItems.getAll();
        for (String key : resultMap.keySet())
        {
            Tariff tariff = (Tariff) resultMap.get(key);
            tariffs.add(tariff);
        }

    }

    @Override
    public void close() throws IOException
    {
        List<String> headers = new ArrayList<>();
        List<Map<String, String>> datas = new ArrayList<>();

        headers.add("商品编码");
        headers.add("商品名称");
        headers.add("国家");
        headers.add("申报要素");
        headers.add("进口关税率");
        headers.add("进口增值税率");
        headers.add("出口关税率");
        headers.add("出口退税率");
        headers.add("法定第一单位");
        headers.add("法定第二单位");
        headers.add("海关监管条件");
        headers.add("商品描述");

        for (Tariff tariff : tariffs)
        {
            Map<String, String> data = new HashMap<>();

            data.put("商品编码", tariff.getCode());
            data.put("商品名称", tariff.getName());
            data.put("国家", tariff.getCountry());
            data.put("申报要素", tariff.getSpecific());
            data.put("进口关税率", tariff.getImportTariffRate());
            data.put("进口增值税率", tariff.getImportVatRate());
            data.put("出口关税率", tariff.getExportTariffRate());
            data.put("出口退税率", tariff.getExportRebateRate());
            data.put("法定第一单位", tariff.getUnit1());
            data.put("法定第二单位", tariff.getUnit2());
            data.put("海关监管条件", tariff.getCondition());
            data.put("商品描述", tariff.getDescription());

            datas.add(data);
            System.out.println(tariff.toString());
        }

        ExcelKit.write("d:/canada.xls", headers, datas);

        tariffs.clear();
    }

}
