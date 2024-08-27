import React, { useState } from 'react';
import NGODonationForm from './NGODonationForm';

const NGODonation = ({ campaignTitle }) => {
  const [items, setItems] = useState([]);
  const [selectedItem, setSelectedItem] = useState(null);
  const [isFormOpen, setIsFormOpen] = useState(false);

  // Define handleAddItem
  const handleAddItem = (newItem) => {
    setItems([...items, newItem]);
    setIsFormOpen(false); // Close form after adding item
  };

  // Define handleEditItem
  const handleEditItem = (updatedItem) => {
    setItems(items.map((item) => (item.id === updatedItem.id ? updatedItem : item)));
    setIsFormOpen(false); // Close form after editing item
  };

  // Define handleDeleteItem
  const handleDeleteItem = (itemId) => {
    const isConfirmed = window.confirm('Do you really want to delete this item?');
    if (isConfirmed) {
      setItems(items.filter((item) => item.id !== itemId));
    }
  };

  // Open the form for a new item or an existing item
  const openForm = (item = null) => {
    setSelectedItem(item);
    setIsFormOpen(true);
  };

  // Close the form
  const closeForm = () => {
    setIsFormOpen(false);
    setSelectedItem(null);
  };

  return (
    <div className="p-6">
      <h2 className="text-2xl mb-4">Items for {campaignTitle}</h2>
      <button
        className="bg-green-500 text-white py-2 px-4 rounded mb-4"
        onClick={() => openForm()}
      >
        Add Item
      </button>

      {items.length > 0 ? (
        items.map((item) => (
          <div key={item.id} className="bg-white p-4 rounded-lg shadow-md mb-4">
            <h3 className="text-xl font-semibold">{item.name}</h3>
            <p>{item.description}</p>
            <p>
              <strong>Quantity:</strong> {item.quantity}
            </p>
            <div className="flex space-x-2 mt-2">
              <button
                className="bg-yellow-500 text-white py-1 px-3 rounded"
                onClick={() => openForm(item)}
              >
                Edit
              </button>
              <button
                className="bg-red-500 text-white py-1 px-3 rounded"
                onClick={() => handleDeleteItem(item.id)}
              >
                Delete
              </button>
            </div>
          </div>
        ))
      ) : (
        <p>No items added yet.</p>
      )}

      {isFormOpen && (
        <NGODonationForm
          item={selectedItem}
          onAddItem={handleAddItem}
          onEditItem={handleEditItem} // Ensure this is passed
          onCloseForm={closeForm}
        />
      )}
    </div>
  );
};

export default NGODonation;
