package br.com.egp.envy.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportData {
    List<PieChartData> pieChartDataList;
    List<ChartData> chartDataList;
}
