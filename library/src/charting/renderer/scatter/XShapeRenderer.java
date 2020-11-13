package charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;

import charting.buffer.ScatterBuffer;
import charting.interfaces.datasets.IScatterDataSet;
import charting.utils.Utils;
import charting.utils.ViewPortHandler;

/**
 * Created by wajdic on 15/06/2016.
 * Created at Time 09:08
 */
public class XShapeRenderer implements ShapeRenderer {


    @Override
    public void renderShape(Canvas c, IScatterDataSet dataSet, ViewPortHandler viewPortHandler, ScatterBuffer buffer, Paint
            renderPaint, final float shapeSize) {

        final float shapeHalf = shapeSize / 2f;

        renderPaint.setStyle(Paint.Style.STROKE);
        renderPaint.setStrokeWidth(Utils.convertDpToPixel(1f));

        for (int i = 0; i < buffer.size(); i += 2) {

            if (!viewPortHandler.isInBoundsRight(buffer.buffer[i]))
                break;

            if (!viewPortHandler.isInBoundsLeft(buffer.buffer[i])
                    || !viewPortHandler.isInBoundsY(buffer.buffer[i + 1]))
                continue;

            renderPaint.setColor(dataSet.getColor(i / 2));

            c.drawLine(
                    buffer.buffer[i] - shapeHalf,
                    buffer.buffer[i + 1] - shapeHalf,
                    buffer.buffer[i] + shapeHalf,
                    buffer.buffer[i + 1] + shapeHalf,
                    renderPaint);
            c.drawLine(
                    buffer.buffer[i] + shapeHalf,
                    buffer.buffer[i + 1] - shapeHalf,
                    buffer.buffer[i] - shapeHalf,
                    buffer.buffer[i + 1] + shapeHalf,
                    renderPaint);
        }

    }

}