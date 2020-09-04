package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {
	private ImdbDAO dao;
	private Map<Integer, Director> map;
	private Graph<Director, DefaultWeightedEdge> graph;

	public List<Integer> listYears(){
		return this.dao.listYears();
	}

	public Model() {
	
		this.dao= new ImdbDAO();
		this.map= new HashMap<Integer,Director>();
		this.dao.listAllDirectors(map);
	}
	
	public void creaGrafo(int anno)
	{
		this.graph= new SimpleWeightedGraph<Director, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		if(this.dao.listVertex(anno, map)==null){
			System.out.println("Errore lettura vertex");
			return;
		}
		
		Graphs.addAllVertices(this.graph, this.dao.listVertex(anno, map));
		
		
		if(this.dao.listAdiacenze(anno, map)==null){
			System.out.println("Errore lettura edges");
			return;
		}
		
		for(Adiacenza a : this.dao.listAdiacenze(anno, map)){
			Graphs.addEdge(this.graph, a.getD1(), a.getD2(), a.getPeso());
		}
		
	}
	public List<Director> setV(){
		List<Director> list = new  ArrayList<Director>(this.graph.vertexSet());
		Collections.sort(list);
		return list ;
	}	
	public Set<DefaultWeightedEdge> setE(){
		return this.graph.edgeSet();
	}
	
	public List<Vicino> registiAd(Director value) {
		List<Vicino> list = new ArrayList<>();
		
	for(Director a : Graphs.neighborListOf(this.graph, value)){
		list.add(new Vicino(a, (int) this.graph.getEdgeWeight(this.graph.getEdge(a, value))));
	}
	Collections.sort(list);
		return list;
	}
}
