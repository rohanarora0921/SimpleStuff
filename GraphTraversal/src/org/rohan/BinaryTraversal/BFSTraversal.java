package org.rohan.BinaryTraversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BFSTraversal {

	BinaryNode root;		//temporary node holder for traversal and other purposes
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

	
	public  void add(int data)
	{
		root=add(root,data);
	}
	
	public static BinaryNode add(BinaryNode node, int data) {
		
				
		if(node==null)								//in case this is the first node to be added
		{
			node=new BinaryNode(data);		//constructor of BinaryNode called to store data and return a new node
						
		}
		else {
			///IF not 1st element, flow enters this part
			if(node.left==null && node.right==null)				//checks if both children are missing, and if yes, left position is chosen over right to be added to
			{
				node.left=add(node.right,data);
			}
			else if (node.right == null) {							//in case both children are not null, and if left is taken, check for right child's existence
				node.right=add(node.right, data);
				
			} else {															//if all else fails, insert in left child
				node.left=add(node.left, data);
				
			} 
		}
		return node;
	}
	
	public void preOrder()
	{
		preOrder(root);
	}
	
	private void preOrder(BinaryNode node) {
		// TODO Auto-generated method stub
		if(node!=null)
		{
			System.out.println("Node data: "+node.data);
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	public void inOrder()
	{
		inOrder(root);
		System.out.println();
	}
	
	private void inOrder(BinaryNode node) {
		// TODO Auto-generated method stub
		if(node!=null)
		{
			inOrder(node.left);
			System.out.println("Node data: "+node.data);
			inOrder(node.right);
		}
	}
	
	public void postOrder()
	{
		postOrder(root);
		System.out.println();
	}
	
	private void postOrder(BinaryNode node) {
		// TODO Auto-generated method stub
		if(node!=null)
		{
			
			postOrder(node.left);
			postOrder(node.right);
			System.out.println("Node data: "+node.data);
		}
	}
	
	public void bfs()
	{
		bfs(root);
	}


	private void bfs(BinaryNode node) {
		// TODO Auto-generated method stub
		Queue<BinaryNode> q = new ArrayDeque<>();
		q.add(node);				//adds first node to the queue for BFS
		while(!q.isEmpty())		//until stack is empty, i.e. until the entire tree is traversed
		{
			BinaryNode temp = q.poll();		//take the first node in queue, and check its validity
			if(temp==null) return;
			System.out.println(temp.data);			//print current parents data 
			if(temp.left!=null) q.offer(temp.left);	//offer current parents left node in queue
			if(temp.right!=null) q.offer(temp.right);	//offer current parents right node in queue
			
		}
		
	}
}
