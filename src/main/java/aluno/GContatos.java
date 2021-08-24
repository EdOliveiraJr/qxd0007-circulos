package aluno;

import cliente.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import aluno.base.Circulo;
import aluno.base.Contato;

public class GContatos implements IContatosManager, ICirculosManager, ICirculoOperationsManager {

	List <CirculoBase> allCirculos = new ArrayList<>();
	List <ContatoBase> allContatos = new ArrayList<>();
 
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
		for (CirculoBase circulo : allCirculos) {
			if(circulo.getId().equals(id)){
				return false;
			}
		}
		allCirculos.add(new Circulo(id, limite));
		return true;
	}

	@Override
	public boolean updateCircle(CirculoBase circulo) {
		return false;
	}

	@Override
	public CirculoBase getCircle(String idCirculo) {
		for (CirculoBase circuloBase : allCirculos) {
			if(circuloBase.getId().equals(idCirculo)){
				return circuloBase;
			}
		}
		return null;
	}

	@Override
	public List<CirculoBase> getAllCircles() {
		return allCirculos;
	}

	@Override
	public boolean removeCircle(String idCirculo) {
		for (CirculoBase circulo : allCirculos) {
			if(circulo.getId().equals(idCirculo)){
				allCirculos.remove(circulo);
				return true;
			}
		}
		return false;
	}

	@Override
	public int getNumberOfCircles() {
		return allCirculos.size();
	}

	@Override
	public boolean createContact(String id, String email) {
		for (ContatoBase contato : allContatos) {
			if(contato.getId().equals(id)){
				return false;
			}
		}
		allContatos.add(new Contato(id, email));
		return true;
	}

	@Override
	public List<ContatoBase> getAllContacts() {
		return allContatos;
	}

	@Override
	public boolean updateContact(ContatoBase contato) {
		return false;
	}

	@Override
	public boolean removeContact(String id) {
		for (ContatoBase contatoBase : allContatos) {
			if(contatoBase.getId().equals(id)){
				allContatos.remove(contatoBase);
				return true;
			}
		}
		return false;
	}

	@Override
	public ContatoBase getContact(String id) {
		for (ContatoBase contatoBase : allContatos) {
			if(contatoBase.getId().equals(id)){
				return contatoBase;
			}
		}
		return null;
	}

	@Override
	public int getNumberOfContacts() {
		return allContatos.size();
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
