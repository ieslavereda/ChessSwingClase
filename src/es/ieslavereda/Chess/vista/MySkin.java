package es.ieslavereda.Chess.vista;

import com.alee.api.resource.ClassResource;
import com.alee.managers.style.XmlSkin;

public final class MySkin extends XmlSkin {

	public MySkin() {
		super ( new ClassResource ( MySkin.class, "ExampleSkin.xml" ) );
	}

}
