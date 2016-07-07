package org.rohan.BinaryTraversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BFSTraversal {

	BinaryNode root;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

	
	public  void add(int data)
	{
		root=add(root,data);
	}
	
	public static BinaryNode add(BinaryNode node, int data) {
		
				
		if(node==null)
		{
			node=new BinaryNode(data);
						
		}
		else {
			///IF not 1st element, flow enters this part
			if(node.left==null && node.right==null)
			{
				node.left=add(node.right,data);
			}
			else if (node.right == null) {
				node.right=add(node.right, data);
				
			} else {
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
		q.add(node);
		while(!q.isEmpty())
		{
			BinaryNode temp = q.poll();
			if(temp==null) return;
			System.out.println(temp.data);
			if(temp.left!=null) q.offer(temp.left);
			if(temp.right!=null) q.offer(temp.right);
			
		}
		
	}
}
