package es.ieslavereda.Chess.vista;

import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.JComponent;

import com.alee.painter.decoration.IDecoration;
import com.alee.painter.decoration.background.AbstractBackground;
import com.alee.utils.GraphicsUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias ( "ComponentBackground" )
public class ComponentBackground<C extends JComponent, D extends IDecoration<C, D>, I extends ComponentBackground<C, D, I>>
        extends AbstractBackground<C, D, I>
{
    @Override
    public void paint ( final Graphics2D g2d, final Rectangle bounds, final C c, final D d, final Shape shape )
    {
        final float opacity = getOpacity(c,d);
        if ( opacity > 0 )
        {
            final Composite oc = GraphicsUtils.setupAlphaComposite ( g2d, opacity, opacity < 1f );
            final Paint op = GraphicsUtils.setupPaint ( g2d, c.getBackground () );
            g2d.fill ( shape );
            GraphicsUtils.restorePaint ( g2d, op );
            GraphicsUtils.restoreComposite ( g2d, oc, opacity < 1f );
        }
    }
}
