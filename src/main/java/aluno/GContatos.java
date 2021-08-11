package aluno;

import cliente.*;

import java.util.List;

public class GContatos implements IContatosManager, ICirculosManager, ICirculoOperationsManager {

	@Override
	public boolean tie(String idContato, String idCirculo) throws CirculoNotFoundException, ContatoNotFoundException {
		return false;
	}

	@Override
	public boolean untie(String idContato, String idCirculo) throws CirculoNotFoundException, ContatoNotFoundException {
		return false;
	}

	@Override
	public List<ContatoBase> getContacts(String id) throws CirculoNotFoundException {
		return null;
	}

	@Override
	public List<CirculoBase> getCircles(String id) throws ContatoNotFoundException {
		return null;
	}

	@Override
	public List<CirculoBase> getCommomCircle(String idContato1, String idContato2) throws ContatoNotFoundException {
		return null;
	}

	@Override
	public boolean createCircle(String id, int limite) {
		return false;
	}

	@Override
	public boolean updateCircle(CirculoBase circulo) {
		return false;
	}

	@Override
	public CirculoBase getCircle(String idCirculo) {
		return null;
	}

	@Override
	public List<CirculoBase> getAllCircles() {
		return null;
	}

	@Override
	public boolean removeCircle(String idCirculo) {
		return false;
	}

	@Override
	public int getNumberOfCircles() {
		return 0;
	}

	@Override
	public boolean createContact(String id, String email) {
		return false;
	}

	@Override
	public List<ContatoBase> getAllContacts() {
		return null;
	}

	@Override
	public boolean updateContact(ContatoBase contato) {
		return false;
	}

	@Override
	public boolean removeContact(String id) {
		return false;
	}

	@Override
	public ContatoBase getContact(String id) {
		return null;
	}

	@Override
	public int getNumberOfContacts() {
		return 0;
	}

	@Override
	public boolean favoriteContact(String idContato) {
		return false;
	}

	@Override
	public boolean unfavoriteContact(String idContato) {
		return false;
	}

	@Override
	public boolean isFavorited(String id) {
		return false;
	}

	@Override
	public List<ContatoBase> getFavorited() {
		return null;
	}
}
