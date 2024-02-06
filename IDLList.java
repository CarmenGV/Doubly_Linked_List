import java.util.ArrayList;
public class IDLList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices = new ArrayList<Node<E>>();
	
	// ******************************** INNER CLASS NODE ********************************
	// Creates new node and sets values for new node's data, next, and prev
	private class Node<E>
	{
		E data;
		Node<E> next;
		Node<E> prev;
		
		public Node(E elem)
		{
			data = elem;
			next = null;
			prev = null;
		}
		public Node(E elem, Node<E> prev, Node<E> next)
		{
			this.data = elem;
			this.next = next;
			this.prev = prev;
		}
	}
	// ******************************** CONSTRUCTOR ********************************
	// Creates empty doubly-linked list
	public IDLList()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	// ******************************** ADD METHOD 1 ********************************
	// Adds new node with data elem at specified index
	public boolean add(int index, E elem)
	{
		try
		{
			// Check if index is 0 (head)
			if(index == 0)
			{
				this.add(elem);
			}
			
			// Reassign currentNode, and currentNode's, newNode's, and prevNode's prev and next
			Node<E> newNode = new Node<E>(elem);
			Node<E> currentNode = this.indices.get(index);
			Node<E> prevNode = this.indices.get(index).prev;
			prevNode.next = newNode;
			newNode.prev = prevNode;
			currentNode.prev = newNode;
			newNode.next = currentNode;
			
			// Reassign indices in ArrayList
			this.indices.add(newNode);
			int i = this.indices.size() -1;
			for(; i >= index; --i)
			{
				Node<E> element = this.indices.get(i - 1);
				this.indices.set(i, element);
			}
			
			// Assign new node to indicated index
			this.indices.set(index, newNode);
			size = this.indices.size();
			return true;
			
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	// ******************************** ADD METHOD 2 ********************************
	// Adds new node with data "elem" to head of list
	public boolean add(E elem)
	{
		// Create new node
		Node<E> newNode = new Node<E>(elem);
		
		// Check if list is empty
		if(head == null)
		{
			head = newNode;
			tail = newNode;
			head.prev = null;
			tail.next = null;
			this.indices.add(newNode);
			size = 1;
			return true;
		} else // List is not empty; add new node to head of list
		{
			// Reassign head, prev, and next for head and new Node
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
			
			// Move elements in list over to the right one
			this.indices.add(newNode);
			int i = this.indices.size() - 1;
			for(; i > 0; --i)
			{
				Node<E> element = this.indices.get(i - 1);
				this.indices.set(i, element);
			}
			
			// Reassign head node in ArrayList
			this.indices.set(0, head);
			size = this.indices.size();
			return true;
		}
	}
	
	// ******************************** APPEND METHOD ********************************
	// Add new node to end of list
	public boolean append(E elem)
	{
		try
		{
			Node<E> newNode = new Node<E>(elem);
			
			// Check is list is empty
			if(head == null)
			{
				this.add(elem);
				return true;
			} else
			{
				// Set tail and newNode, and prev and next of tail and new node
				this.tail.next = newNode;
				newNode.prev = this.tail;
				tail = newNode;
				
				// Add new node to end of list
				this.indices.add(newNode);
				size = this.indices.size();
				return true;
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	// ******************************** GET METHOD ********************************
	// Returns element at specified index
	public E get(int index)
	{
		try
		{
			E element = this.indices.get(index).data;
			return element;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	// ******************************** GET HEAD METHOD ********************************
	// Returns element at head of list
	public E getHead()
	{
		try
		{
			// Check if list is empty
			if(head == null)
			{
				return null;
			}
			// Else, return first element in the list
			else
			{
				E headElement = this.indices.get(0).data;
				return headElement;
			}
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	// ******************************** GET LAST METHOD ********************************
	// Returns element at tail of list
	public E getLast()
	{
		try
		{
			// Check if list is empty
			if(tail == null)
			{
				return null;
			}
			// Else, return last element in list
			else
			{
				int lastIndex = this.indices.size() - 1;
				E tailElement = this.indices.get(lastIndex).data;
				return tailElement;
			}
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	// ******************************** SIZE METHOD ********************************
	// Returns list size
	public int size()
	{
		try
		{
			// Get list size and return it
			int listSize = this.indices.size();
			return listSize;
			
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	// ******************************** REMOVE METHOD 1 ********************************
	// Remove: Removes and returns the element at the head of the list
	public E remove()
	{
		try
		{
			// Check if list is empty
			if(head == null)
			{
				return null;
			} 
			// Check if list has only one element
			else if(this.indices.size() == 1)
			{
				E headData = head.data;
				this.indices.remove(0);
				head = null;
				tail = null;
				size -= 1;
				return headData;
			}
			// Else, for lists with more than one element...
			else
			{
				Node<E> nextNode = this.indices.get(1);
				if(this.indices.size() >= 3)
				{
					Node<E> nodeAfter = this.indices.get(2);
					head.next = nodeAfter;
				}
				E headData = head.data;
				head = nextNode;
				head.prev = null;
				this.indices.remove(0);
				size -= 1;
				return headData;
			}
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	// ******************************** REMOVE METHOD 2 ********************************
	// RemoveLast: Removes and returns the element at the tail of the list
	public E removeLast()
	{
		try
		{
			//Check if list is empty
			if(tail == null)
			{
				return null;
			}
			// Check if list has only one element
			else if(this.indices.size() == 1)
			{
				E tailData = tail.data;
				this.indices.remove(0);
				head = null;
				tail = null;
				size -= 1;
				return tailData;
			} 
			// Else, for lists with more than one element...
			else
			{
				int lastIndex = this.indices.size() - 1;
				Node<E> prevNode = this.indices.get(lastIndex - 1);
				if(this.indices.size() >= 3)
				{
					Node<E> nodeBefore = this.indices.get(lastIndex - 2);
					tail.prev = nodeBefore;
				}
				E tailData = tail.data;
				tail = prevNode;
				tail.next = null;
				this.indices.remove(lastIndex);
				size -= 1;
				return tailData;
			}
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	// ******************************** REMOVE METHOD 3 ********************************
	// RemoveAt: Removes and returns the element at indicated index
	public E removeAt(int index)
	{
		try
		{
			// Check is list is empty
			if(this.indices.size() == 0)
			{
				return null;
			}
			
			// Check if only one element in list and if index equals 0
			else if(this.indices.size() == 1 && index == 0)
			{
				E currentNodeData = this.indices.get(index).data;
				this.indices.remove(index);
				head = null;
				tail = null;
				size -= 1;
				return currentNodeData;
			}
			
			// Else, remove element at indicated index
			else
			{
				// But first check that index is not out of bounds
				if(index >= this.indices.size())
				{
					return null;
				} else
				{
					int listSize = this.indices.size();
					E currentNodeData = this.indices.get(index).data;
					Node<E> prevNode = this.indices.get(index - 1);
					if(index == listSize - 1)
					{
						prevNode.next = null;
						tail = prevNode;
					} else
					{
						Node<E> nextNode = this.indices.get(index + 1);
						prevNode.next = nextNode;
						nextNode.prev = prevNode;
					}
					this.indices.remove(index);
					size -= 1;
					return currentNodeData;
				}
			}
			
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	// ******************************** REMOVE METHOD 4 ********************************
	// Remove Element: Removes first instance of an element and returns true (false if no instance of element exists)
	public boolean remove(E elem) {
		try
		{
			// Check if list is empty
			if(this.indices.size() == 0)
			{
				return false;
			}
			else
			{
				E currentNodeData = null;
				int index = -1;
				for(int i = 0; i < this.indices.size(); ++i)
				{
					if(this.indices.get(i).data == elem)
					{
						currentNodeData = elem;
						index = i;
						break;
					}
				}
				if(currentNodeData != null && index != -1)
				{
					this.removeAt(index);
					return true;
				} else
				{
					return false;
				}
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	// ******************************** STRING METHOD ********************************
	// String Method: Outputs list elements as a string.
	public String toString()
	{
		try
		{
			// Check if list is empty
			if(head == null && tail == null)
			{
				return null;
			}else
			{
				String arrayToString = "";
				for(int i = 0; i < this.size; ++i)
				{
					if(i == this.size - 1)
					{
						arrayToString += this.indices.get(i).data;
					} else
					{
						arrayToString += this.indices.get(i).data.toString() + ", ";
					}
				}
				return arrayToString;
			}
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
