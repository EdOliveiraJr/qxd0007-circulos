package cliente;

import org.graalvm.compiler.replacements.nodes.arithmetic.IntegerExactArithmeticSplitNode;

public class CirculoNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7331861696548215622L;

	private final String id;

	public CirculoNotFoundException(String circuloId) {
		this.id = circuloId;
	}

	public String getCirculoNaoEncontrado() {
		return id;
	}
}
