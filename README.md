Project Description
The project's goal is to implement a Merkle Tree-based system to ensure the integrity and authenticity of data within a larger dataset.

The project includes five main classes:
1. HashUtil
Purpose: Provides methods for computing MD5 cryptographic hashes.
Key Methods:
•	dataToHash(Object data): Computes the MD5 hash of the hashCode value of the data object.
•	computeMD5(byte[] input): Computes the MD5 hash of a byte array.
•	intToBytes(int value): Converts an integer to a byte array (big-endian).

2. HashLinkedList
Purpose: Implements an advanced linked list structure with MD5 hash computation and management for each element.
Key Methods:
•	addAtHead(T data): Adds an element to the head of the list.
•	addAtTail(T data): Adds an element to the tail of the list.
•	remove(T data): Removes the first occurrence of the specified data.
•	getAllHashes(): Returns a sorted list of all MD5 hashes in the list.
•	buildNodesString(): Builds a textual representation of the list, showing data and corresponding hashes.
•	iterator(): Returns a fail-fast iterator for safe traversal.

3. MerkleNode
Purpose: Represents a node in a Merkle Tree.
Key Methods:
•	Constructors:
o	Leaf node: Accepts a hash as a parameter.
o	Branch node: Accepts a hash and two child nodes.
•	isLeaf(): Checks if the node is a leaf.
•	getHash(), getLeft(), getRight(): Retrieve the hash, left child, and right child, respectively.

4. MerkleProof
Purpose: Manages Merkle Proofs for validating data or branches in a tree.
Key Methods:
•	Constructor:
o	Accepts the root hash and the maximum proof length.
•	addHash(String hash, boolean isLeft): Adds a hash to the proof, specifying whether it should be concatenated on the left.
•	proveValidityOfData(Object data): Validates data using the proof.
•	proveValidityOfBranch(MerkleNode branch): Validates a branch using the proof.

5. MerkleTree
Purpose: Represents and manages a complete Merkle Tree.
Key Methods:
•	Constructor: Builds the tree from a HashLinkedList.
•	getRoot(), getWidth(), getHeight(): Retrieve the root, width, and height of the tree.
•	validateData(T data): Verifies if data belongs to the tree.
•	validateBranch(MerkleNode branch): Verifies if a branch is valid in the tree.
•	getMerkleProof(T data): Generates a Merkle Proof for the data.
