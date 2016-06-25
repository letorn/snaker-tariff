package common;

public class Tariff
{

    private String code;// 商品编码
    private String name;// 商品名称
    private String country;// 国家
    private String specific;// 申报要素
    private String importTariffRate;// 进口关税率
    private String importVatRate;// 进口增值税率
    private String exportTariffRate;// 出口关税率
    private String exportRebateRate;// 出口退税率
    private String unit1;// 法定第一单位
    private String unit2;// 法定第二单位
    private String condition;// 海关监管条件
    private String description;// 商品描述

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUnit1()
    {
        return unit1;
    }

    public void setUnit1(String unit1)
    {
        this.unit1 = unit1;
    }

    public String getUnit2()
    {
        return unit2;
    }

    public void setUnit2(String unit2)
    {
        this.unit2 = unit2;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getSpecific()
    {
        return specific;
    }

    public void setSpecific(String specific)
    {
        this.specific = specific;
    }

    public String getExportTariffRate()
    {
        return exportTariffRate;
    }

    public void setExportTariffRate(String exportTariffRate)
    {
        this.exportTariffRate = exportTariffRate;
    }

    public String getExportRebateRate()
    {
        return exportRebateRate;
    }

    public void setExportRebateRate(String exportRebateRate)
    {
        this.exportRebateRate = exportRebateRate;
    }

    public String getCondition()
    {
        return condition;
    }

    public void setCondition(String condition)
    {
        this.condition = condition;
    }

    public String getImportTariffRate()
    {
        return importTariffRate;
    }

    public void setImportTariffRate(String importTariffRate)
    {
        this.importTariffRate = importTariffRate;
    }

    public String getImportVatRate()
    {
        return importVatRate;
    }

    public void setImportVatRate(String importVatRate)
    {
        this.importVatRate = importVatRate;
    }

    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();
        buf.append(String.format("商品编码: %s", this.getCode()));
        buf.append(String.format(", 商品名称: %s", this.getName()));
        buf.append(String.format(", 国家: %s", this.getCountry()));
        buf.append(String.format(", 申报要素: %s", this.getSpecific()));
        buf.append(String.format(", 进口关税率: %s", this.getImportTariffRate()));
        buf.append(String.format(", 进口增值税率: %s", this.getImportVatRate()));
        buf.append(String.format(", 出口关税率: %s", this.getExportTariffRate()));
        buf.append(String.format(", 出口退税率: %s", this.getExportRebateRate()));
        buf.append(String.format(", 法定第一单位: %s", this.getUnit1()));
        buf.append(String.format(", 法定第二单位: %s", this.getUnit2()));
        buf.append(String.format(", 海关监管条件: %s", this.getCondition()));
        buf.append(String.format(", 商品描述: %s", this.getDescription()));

        return buf.toString();
    }

}
