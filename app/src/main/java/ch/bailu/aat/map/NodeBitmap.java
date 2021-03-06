package ch.bailu.aat.map;


import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;

import ch.bailu.aat.util.graphic.SyncTileBitmap;
import ch.bailu.aat.util.ui.AppDensity;
import ch.bailu.aat.util.ui.AppLog;

public class NodeBitmap {

    private static final int STROKE_WIDTH=MapPaint.EDGE_WIDTH;
    private static final int RADIUS=5;

    private static final SparseArray<NodeBitmap> nodes = new SparseArray<>(10);

    public static NodeBitmap get(AppDensity res) {
        int radius = res.toDPi(RADIUS);

        NodeBitmap node = nodes.get(radius);

        if (node == null) {
            node = new NodeBitmap(radius, res);
            nodes.append(radius, node);
            AppLog.d(node, "Nodes: " + nodes.size());
        }

        return node;
    }

    private final SyncTileBitmap bitmap = new SyncTileBitmap();

    private NodeBitmap(int radius, AppDensity res) {

        final int color = Color.WHITE;

        int stroke_width = res.toDPi(STROKE_WIDTH);
        int hsize = (radius+ stroke_width);
        int size = hsize * 2;



        bitmap.set(size, true);

        Canvas canvas = bitmap.getAndroidCanvas();
        Paint stroke = AndroidGraphicFactory.getPaint(MapPaint.createEdgePaint(res));
        stroke.setAntiAlias(true);

        Paint fill = new Paint();
        fill.setAntiAlias(false);
        fill.setStyle(Style.FILL);
        fill.setColor(color);

        canvas.drawCircle(hsize,hsize, radius, fill);
        canvas.drawCircle(hsize, hsize, radius, stroke);
    }


    public Bitmap getTileBitmap() {
        return bitmap.getTileBitmap();
    }


    public synchronized void draw(Point p, Canvas c, int color, Resources r) {
        Drawable drawable = bitmap.getDrawable(r);

        AndroidDraw.centerDrawable(drawable, p);
        drawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        drawable.draw(c);
    }
}