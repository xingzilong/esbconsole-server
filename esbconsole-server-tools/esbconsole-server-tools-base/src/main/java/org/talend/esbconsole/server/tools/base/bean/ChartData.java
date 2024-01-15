package org.talend.esbconsole.server.tools.base.bean;

import java.time.LocalDate;

public interface ChartData {


    public LocalDate getDate();

    public void setDate(LocalDate date);

    public Long getTotal();

    public void setTotal(Long total);

    public double getBestData();

    public void setBestData(double bestData);

    public String getUnit();

    public void setUnit(String unit);
}
