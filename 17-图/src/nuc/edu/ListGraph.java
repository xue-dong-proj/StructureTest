package nuc.edu;

import nuc.MinHeap;
import nuc.UnionFind;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


@SuppressWarnings("unchecked")
public class ListGraph<V, E> extends Graph<V, E> {
	public ListGraph() {}
	public ListGraph(WeightManager<E> weightManager) {
		super(weightManager);
	}

	private static class Vertex<V, E> {
		V value;
		Set<Edge<V, E>> inEdges = new HashSet<>();
		Set<Edge<V, E>> outEdges = new HashSet<>();
		Vertex(V value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object obj) {
			return Objects.equals(value, ((Vertex<V, E>)obj).value);
		}
		@Override
		public int hashCode() {
			return value == null ? 0 : value.hashCode();
		}
		@Override
		public String toString() {
			return value == null ? "null" : value.toString();
		}
	}
	
	private static class Edge<V, E> {
		Vertex<V, E> from;
		Vertex<V, E> to;
		E weight;
		
		Edge(Vertex<V, E> from, Vertex<V, E> to) {
			this.from = from;
			this.to = to;
		}
		
		EdgeInfo<V, E> info() {
			return new EdgeInfo<>(from.value, to.value, weight);
		}
		
		@Override
		public boolean equals(Object obj) {
			Edge<V, E> edge = (Edge<V, E>) obj;
			return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
		}
		@Override
		public int hashCode() {
			return from.hashCode() * 31 + to.hashCode();
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
	
	private Map<V, Vertex<V, E>> vertices = new HashMap<>();
	private Set<Edge<V, E>> edges = new HashSet<>();
	private Comparator<Edge<V, E>> edgeComparator = (Edge<V, E> e1, Edge<V, E> e2) -> {
		return weightManager.compare(e1.weight, e2.weight);
	};
	
	public void print() {
		System.out.println("[顶点]-------------------");
		vertices.forEach((V v, Vertex<V, E> vertex) -> {
			System.out.println(v);
			System.out.println("out-----------");
			System.out.println(vertex.outEdges);
			System.out.println("in-----------");
			System.out.println(vertex.inEdges);
		});

		System.out.println("[边]-------------------");
		edges.forEach((Edge<V, E> edge) -> {
			System.out.println(edge);
		});
	}

	@Override
	public int edgesSize() {
		return edges.size();
	}

	@Override
	public int verticesSize() {
		return vertices.size();
	}

	@Override
	public void addVertex(V v) {
		if (vertices.containsKey(v)) return;
		vertices.put(v, new Vertex<>(v));
	}

	@Override
	public void addEdge(V from, V to) {
		addEdge(from, to, null);
	}

	@Override
	public void addEdge(V from, V to, E weight) {
		Vertex<V, E> fromVertex = vertices.get(from);
		if (fromVertex == null) {
			fromVertex = new Vertex<>(from);
			vertices.put(from, fromVertex);
		}
		
		Vertex<V, E> toVertex = vertices.get(to);
		if (toVertex == null) {
			toVertex = new Vertex<>(to);
			vertices.put(to, toVertex);
		}

		Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
		edge.weight = weight;
		if (fromVertex.outEdges.remove(edge)) {
			toVertex.inEdges.remove(edge);
			edges.remove(edge);
		}
		fromVertex.outEdges.add(edge);
		toVertex.inEdges.add(edge);
		edges.add(edge);
	}

	@Override
	public void removeEdge(V from, V to) {
		Vertex<V, E> fromVertex = vertices.get(from);
		if (fromVertex == null) return;
		
		Vertex<V, E> toVertex = vertices.get(to);
		if (toVertex == null) return;
		
		Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
		if (fromVertex.outEdges.remove(edge)) {
			toVertex.inEdges.remove(edge);
			edges.remove(edge);
		}
	}

	@Override
	public void removeVertex(V v) {
		Vertex<V, E> vertex = vertices.remove(v);
		if (vertex == null) return;

		for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext();) {
			Edge<V, E> edge = iterator.next();
			edge.to.inEdges.remove(edge);
			// 将当前遍历到的元素edge从集合vertex.outEdges中删掉
			iterator.remove(); 
			edges.remove(edge);
		}

		for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext();) {
			Edge<V, E> edge = iterator.next();
			edge.from.outEdges.remove(edge);
			// 将当前遍历到的元素edge从集合vertex.inEdges中删掉
			iterator.remove(); 
			edges.remove(edge);
		}
	}

	@Override
	public void bfs(V begin, VertexVisitor<V> visitor) {
		if (visitor == null) return;
		Vertex<V, E> beginVertex = vertices.get(begin);
		if (beginVertex == null) return;
		
		Set<Vertex<V, E>> visitedVertices = new HashSet<>();
		Queue<Vertex<V, E>> queue = new LinkedList<>();
		queue.offer(beginVertex);
		visitedVertices.add(beginVertex);
		
		while (!queue.isEmpty()) {
			Vertex<V, E> vertex = queue.poll();
			if (visitor.visit(vertex.value)) return;
			
			for (Edge<V, E> edge : vertex.outEdges) {
				if (visitedVertices.contains(edge.to)) continue;
				queue.offer(edge.to);
				visitedVertices.add(edge.to);
			}
		}
	}

	@Override
	public void dfs(V begin, VertexVisitor<V> visitor) {
		if (visitor == null) return;
		Vertex<V, E> beginVertex = vertices.get(begin);
		if (beginVertex == null) return;

		Set<Vertex<V, E>> visitedVertices = new HashSet<>();
		Stack<Vertex<V, E>> stack = new Stack<>();
		
		// 先访问起点
		stack.push(beginVertex);
		visitedVertices.add(beginVertex);
		if (visitor.visit(begin)) return;
		
		while (!stack.isEmpty()) {
			Vertex<V, E> vertex = stack.pop();
			
			for (Edge<V, E> edge : vertex.outEdges) {
				if (visitedVertices.contains(edge.to)) continue;
				
				stack.push(edge.from);
				stack.push(edge.to);
				visitedVertices.add(edge.to);
				if (visitor.visit(edge.to.value)) return;
				
				break;
			}
		}
	}

	@Override
	public List<V> topologicalSort() {
		List<V> list = new ArrayList<>();
		Queue<Vertex<V, E>> queue = new LinkedList<>();
		Map<Vertex<V, E>, Integer> ins = new HashMap<>();
		// 初始化（将度为0的节点都放入队列）
		vertices.forEach((V v, Vertex<V, E> vertex) -> {
			int in = vertex.inEdges.size();
			if (in == 0) {
				queue.offer(vertex);
			} else {
				ins.put(vertex, in);
			}
		});
		
		while (!queue.isEmpty()) {
			Vertex<V, E> vertex = queue.poll();
			// 放入返回结果中
			list.add(vertex.value);
			
			for (Edge<V, E> edge : vertex.outEdges) {
				int toIn = ins.get(edge.to) - 1;
				if (toIn == 0) {
					queue.offer(edge.to);
				} else {
					ins.put(edge.to, toIn);
				}
			}
		}
		
		return list;
	}

	@Override
	public Set<EdgeInfo<V, E>> mst() {
		return Math.random() > 0.5 ? prim() : kruskal();
	}
	
	private Set<EdgeInfo<V, E>> prim() {
		Iterator<Vertex<V,E>> it = vertices.values().iterator();
		if (!it.hasNext()){
			return null;
		}
		Set<Vertex<V,E>> addedVertex = new HashSet<>();
		Set<EdgeInfo<V,E>> edgeInfos = new HashSet<>();

		Vertex<V,E> vertex = it.next();
		addedVertex.add(vertex);
		MinHeap<Edge<V,E>> heap = new MinHeap<>(vertex.outEdges,edgeComparator);
		int edgeSize = vertices.size() - 1;
		while (!heap.isEmpty() && edgeInfos.size() < edgeSize){
			Edge<V,E> edge = heap.remove();
			if (addedVertex.contains(edge.to)){
				continue;
			}
			edgeInfos.add(edge.info());
			addedVertex.add(edge.to);
			heap.addAll(edge.to.outEdges);
		}
		return edgeInfos;
	}

	private Set<EdgeInfo<V, E>> kruskal(){
		int edgeSize = vertices.size() - 1;
		if (edgeSize == -1){
			return null;
		}
		Set<EdgeInfo<V,E>> edgeInfos = new HashSet<>();
		MinHeap<Edge<V,E>> heap = new MinHeap<>(edges,edgeComparator);
		UnionFind<Vertex<V,E>> uf = new UnionFind<>();
		vertices.forEach((V v,Vertex<V,E> vertex) ->{
			uf.makeSet(vertex);
		});
		while (!heap.isEmpty() && edgeInfos.size() < edgeSize){
			Edge<V, E> edge = heap.remove();
			if (uf.isSame(edge.from, edge.to)) {
				continue;
			}
			edgeInfos.add(edge.info());
			uf.union(edge.from, edge.to);
		}
		return edgeInfos;
	}

	/**
	@Override
	public Map<V, E> shortestPath(V begin) {
		Vertex<V,E> beginVertex = vertices.get(begin);
		if (beginVertex == null){
			return null;
		}
		Map<V,E> selectedPaths = new HashMap<>();
		Map<Vertex<V,E>,E> paths = new HashMap<>();

		for (Edge<V, E> edge : beginVertex.outEdges) {
			paths.put(edge.to,edge.weight);
		}
		while (!paths.isEmpty()){
			Entry<Vertex<V,E>,E> minEntry = getMinPath(paths);
			Vertex<V,E> minVertex = minEntry.getKey();
			selectedPaths.put(minVertex.value, minEntry.getValue());
			paths.remove(minVertex);
			for (Edge<V, E> edge : minVertex.outEdges) {
				if (selectedPaths.containsKey(edge.to.value)){
					continue;
				}
				E newWeight = weightManager.add(minEntry.getValue(),edge.weight);
				E oldWeight = paths.get(edge.to);
				if (oldWeight == null || weightManager.compare(newWeight,oldWeight) < 0){
					paths.put(edge.to,newWeight);
				}
			}
		}
		selectedPaths.remove(begin);
		return selectedPaths;
	}
	*/

	@Override
	public Map<V, Map<V, PathInfo<V, E>>> shortestPath() {
		Map<V, Map<V, PathInfo<V, E>>> paths = new HashMap<>();
		for (Edge<V, E> edge : edges) {
			Map<V, PathInfo<V, E>> map = paths.get(edge.from.value);
			if (map == null){
				map = new HashMap<>();
				paths.put(edge.from.value,map);
			}
			PathInfo<V,E> pathInfo = new PathInfo<>(edge.weight);
			pathInfo.edgeInfos.add(edge.info());
			map.put(edge.to.value,pathInfo);
		}

		vertices.forEach((V v2,Vertex<V,E> vertex2)->{
			vertices.forEach((V v1,Vertex<V,E> vertex1)->{
				vertices.forEach((V v3,Vertex<V,E> vertex3)->{
					if (v1.equals(v2) || v2.equals(v3) || v1.equals(v3)){
						return;
					}
					PathInfo<V,E> path12 = getPathInfo(v1,v2,paths);
					if (path12 == null)	{
						return;
					}

					PathInfo<V,E> path23 = getPathInfo(v1,v2,paths);
					if (path23 == null)	{
						return;
					}

					PathInfo<V,E> path13 = getPathInfo(v1,v2,paths);
					E newWeight = weightManager.add(path12.weight,path23.weight);
					if (path13 != null && weightManager.compare(newWeight,path13.weight ) >= 0)	{
						return;
					}

					if (path13 == null){
						path13 = new PathInfo<V,E>();
						paths.get(v1).put(v3,path13);
					}else {
						path13.edgeInfos.clear();
					}

					path13.weight = newWeight;
					path13.edgeInfos.addAll(path12.edgeInfos);
					path13.edgeInfos.addAll(path23.edgeInfos);

				});
			});
		});
		return paths;
	}

	private PathInfo<V, E> getPathInfo(V from, V to, Map<V, Map<V, PathInfo<V, E>>> paths) {
		Map<V, PathInfo<V, E>> map = paths.get(from);
		return map == null ? null : map.get(to);
	}

	/**
	 * 从paths中挑一个最小的路径出来
	 * @param paths
	 * @return
	 */
	private Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
		Iterator<Entry<Vertex<V, E>, PathInfo<V, E>>> it = paths.entrySet().iterator();
		Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = it.next();
		while (it.hasNext()) {
			Entry<Vertex<V, E>, PathInfo<V, E>> entry = it.next();
			if (weightManager.compare(entry.getValue().weight, minEntry.getValue().weight) < 0) {
				minEntry = entry;
			}
		}
		return minEntry;
	}

//	public Map<V, E> shortestPath(V begin) {
//		Vertex<V, E> beginVertex = vertices.get(begin);
//		if (beginVertex == null) return null;
//		
//		Map<V, E> selectedPaths = new HashMap<>();
//		Map<Vertex<V, E>, E> paths = new HashMap<>();
//		// 初始化paths
//		for (Edge<V, E> edge : beginVertex.outEdges) {
//			paths.put(edge.to, edge.weight);
//		}
//
//		while (!paths.isEmpty()) {
//			Entry<Vertex<V, E>, E> minEntry = getMinPath(paths);
//			// minVertex离开桌面
//			Vertex<V, E> minVertex = minEntry.getKey();
//			selectedPaths.put(minVertex.value, minEntry.getValue());
//			paths.remove(minVertex);
//			// 对它的minVertex的outEdges进行松弛操作
//			for (Edge<V, E> edge : minVertex.outEdges) {
//				// 如果edge.to已经离开桌面，就没必要进行松弛操作
//				if (selectedPaths.containsKey(edge.to.value)) continue;
//				// 新的可选择的最短路径：beginVertex到edge.from的最短路径 + edge.weight
//				E newWeight = weightManager.add(minEntry.getValue(), edge.weight);
//				// 以前的最短路径：beginVertex到edge.to的最短路径
//				E oldWeight = paths.get(edge.to);
//				if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0) {
//					paths.put(edge.to, newWeight);
//				}
//			}
//		}
//		
//		selectedPaths.remove(begin);
//		return selectedPaths;
//	}


//	@Override
//	public void bfs(V begin) {
//		Vertex<V, E> beginVertex = vertices.get(begin);
//		if (beginVertex == null) return;
//		
//		Set<Vertex<V, E>> visitedVertices = new HashSet<>();
//		Queue<Vertex<V, E>> queue = new LinkedList<>();
//		queue.offer(beginVertex);
//		visitedVertices.add(beginVertex);
//		
//		while (!queue.isEmpty()) {
//			Vertex<V, E> vertex = queue.poll();
//			System.out.println(vertex.value);
//			
//			for (Edge<V, E> edge : vertex.outEdges) {
//				if (visitedVertices.contains(edge.to)) continue;
//				queue.offer(edge.to);
//				visitedVertices.add(edge.to);
//			}
//		}
//	}
//
//	@Override
//	public void dfs(V begin) {
//		Vertex<V, E> beginVertex = vertices.get(begin);
//		if (beginVertex == null) return;
//
//		Set<Vertex<V, E>> visitedVertices = new HashSet<>();
//		Stack<Vertex<V, E>> stack = new Stack<>();
//		
//		// 先访问起点
//		stack.push(beginVertex);
//		visitedVertices.add(beginVertex);
//		System.out.println(beginVertex.value);
//		
//		while (!stack.isEmpty()) {
//			Vertex<V, E> vertex = stack.pop();
//			
//			for (Edge<V, E> edge : vertex.outEdges) {
//				if (visitedVertices.contains(edge.to)) continue;
//				
//				stack.push(edge.from);
//				stack.push(edge.to);
//				visitedVertices.add(edge.to);
//				System.out.println(edge.to.value);
//				
//				break;
//			}
//		}
//	}
	
//	public void dfs2(V begin) {
//		Vertex<V, E> beginVertex = vertices.get(begin);
//		if (beginVertex == null) return;
//		dfs2(beginVertex, new HashSet<>());
//	}
//	
//	private void dfs2(Vertex<V, E> vertex, Set<Vertex<V, E>> visitedVertices) {
//		System.out.println(vertex.value);
//		visitedVertices.add(vertex);
//
//		for (Edge<V, E> edge : vertex.outEdges) {
//			if (visitedVertices.contains(edge.to)) continue;
//			dfs2(edge.to, visitedVertices);
//		}
//	}
}
