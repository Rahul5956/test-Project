import React, { useState, useEffect } from 'react';

const NGODonationForm = ({ item, onAddItem, onEditItem, onCloseForm }) => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [quantity, setQuantity] = useState('');

  useEffect(() => {
    if (item) {
      setName(item.name);
      setDescription(item.description);
      setQuantity(item.quantity);
    } else {
      // Clear form for a new item
      setName('');
      setDescription('');
      setQuantity('');
    }
  }, [item]);

  const handleSubmit = (event) => {
    event.preventDefault();
    const newItem = {
      id: item ? item.id : Date.now(),  // Use current item's id if editing
      name,
      description,
      quantity,
    };

    if (item) {
      // Update existing item
      onEditItem(newItem);
    } else {
      // Add new item
      onAddItem(newItem);
    }
  };

  return (
    <div className="fixed inset-0 bg-gray-800 bg-opacity-50 flex items-center justify-center">
      <div className="bg-white p-6 rounded-lg shadow-md w-full max-w-md">
        <h2 className="text-2xl font-semibold mb-4">
          {item ? 'Edit Item' : 'Add New Item'}
        </h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">Item Name</label>
            <input
              type="text"
              className="w-full px-3 py-2 border rounded"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">Description</label>
            <textarea
              className="w-full px-3 py-2 border rounded"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">Quantity</label>
            <input
              type="number"
              className="w-full px-3 py-2 border rounded"
              value={quantity}
              onChange={(e) => setQuantity(e.target.value)}
              required
            />
          </div>
          <div className="flex justify-between">
            <button
              type="button"
              className="bg-red-500 text-white py-2 px-4 rounded"
              onClick={onCloseForm}
            >
              Cancel
            </button>
            <button
              type="submit"
              className="bg-blue-500 text-white py-2 px-4 rounded"
            >
              {item ? 'Update Item' : 'Add Item'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default NGODonationForm;
