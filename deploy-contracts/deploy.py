from web3 import Web3
import json

# Connect to Ganache
ganache_url = "http://127.0.0.1:7545"
w3 = Web3(Web3.HTTPProvider(ganache_url))

# Load the compiled contract ABI and bytecode
with open("contract.json") as f:  # Replace "MyContract.json" with the path to your compiled contract JSON file
    contract_data = json.load(f)
    abi = contract_data["abi"]
    bytecode = contract_data["bytecode"]
    
# print (contract_data)

# Set the default account (one of the accounts created by Ganache)
w3.eth.defaultAccount = w3.eth.accounts[0]

# Deploy the contract
Contract = w3.eth.contract(abi=abi, bytecode=bytecode)
tx_hash = Contract.constructor().transact()
print(tx_hash) 

# # Wait for the transaction to be mined
# tx_receipt = w3.eth.waitForTransactionReceipt(tx_hash)

# # Get the deployed contract address
# contract_address = tx_receipt.contractAddress

# print("Contract deployed at:", contract_address)
