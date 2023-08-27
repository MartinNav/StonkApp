package cz.martinnav.stonkapp

import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


//Code from https://proandroiddev.com/creating-graph-in-jetpack-compose-312957b11b2
@Composable
fun Chart(modifier: Modifier,
          xValues: List<Int>,
          yValues: List<Int>,
          points: List<Double>,
          paddingSpace: Dp,
          verticalStep: Int){
    val pointColor = MaterialTheme.colorScheme.primary
    Box (modifier = modifier
        .background(MaterialTheme.colorScheme.background)
        .padding(8.dp),

        contentAlignment = Alignment.Center){
            Canvas(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.inverseOnSurface)){
                var coordinates: MutableList<PointF> = mutableListOf()
                var controlPoints1: MutableList<PointF> = mutableListOf()
                var controlPoints2: MutableList<PointF> = mutableListOf()
                val xAxisS = (size.width +200.0f/*-paddingSpace.toPx()*/)/xValues.size
                val yaxisS = size.height / yValues.size

                for (i in points.indices){
                    //val x1 = xAxisS * yValues[i]
                    //val x1 = xAxisS * xValues[i]
                    val x1 = (size.width/(xValues.last()-1))*xValues[i]
                    val y1 = (size.height -(yaxisS * (points[i]/verticalStep.toFloat()))).toFloat()
                    coordinates.add(PointF(x1,y1))
                    drawCircle(color= pointColor, radius = 5f,center = Offset(x1,y1))
                }
                for (i in 1 until coordinates.size){
                    controlPoints1.add(PointF((coordinates[i].x + coordinates[i - 1].x) / 2, coordinates[i - 1].y))
                    controlPoints2.add(PointF((coordinates[i].x + coordinates[i - 1].x) / 2, coordinates[i].y))
                }
                val linePath = Path().apply {
                    reset()
                    moveTo(coordinates.first().x,coordinates.first().y)//May need to change
                    for (i in 0 until coordinates.size -1) {
                        cubicTo(
                            controlPoints1[i].x,controlPoints1[i].y,
                            controlPoints2[i].x,controlPoints2[i].y,
                            coordinates[i + 1].x,coordinates[i + 1].y
                        )
                    }
                }
                drawPath(
                    linePath,
                    color = pointColor,
                    style = Stroke(
                        width = 5f,
                        cap = StrokeCap.Round
                    )
                )

            }
    }
}
@Composable
@Preview
fun ChartPreview(){
    Chart(modifier = Modifier.fillMaxWidth().height(300.dp), xValues = listOf(0,1,2,3,4,5,6,7,8), yValues = listOf(0,1,2,3,4,5,6,7), points = listOf(1.2,6.3,4.8,1.3,5.2,5.1,6.0,6.3)/*height of values*/, paddingSpace = 5.dp, verticalStep = 1)
}