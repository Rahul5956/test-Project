import React, { useState } from 'react';
import { useParams } from 'react-router-dom';

const DonationPage = () => {
  const { campaignTitle } = useParams();
  const [quantity, setQuantity] = useState(1);
  const [cart, setCart] = useState([]);
  const [items] = useState([
    { id: 1, name: 'Item A', price: 100 },
    { id: 2, name: 'Item B', price: 200 },
    { id: 3, name: 'Item C', price: 300 }
  ]);

  const handleQuantityChange = (e) => {
    setQuantity(Number(e.target.value));
  };

  const handleAddToCart = (item) => {
    const existingItem = cart.find(cartItem => cartItem.id === item.id);
    if (existingItem) {
      setCart(cart.map(cartItem =>
        cartItem.id === item.id
          ? { ...cartItem, quantity: cartItem.quantity + quantity }
          : cartItem
      ));
    } else {
      setCart([...cart, { ...item, quantity }]);
    }
  };

  const handleRemoveFromCart = (id) => {
    setCart(cart.filter(item => item.id !== id));
  };

  const handlePayment = () => {
    const options = {
      key: 'YOUR_RAZORPAY_KEY', // Replace with your Razorpay key
      amount: cart.reduce((total, item) => total + item.price * item.quantity, 0) * 100,
      currency: 'INR',
      name: 'Donation',
      description: 'Donation Payment',
      handler: function (response) {
        alert('Payment successful!');
      },
      prefill: {
        name: 'Donor Name',
        email: 'donor@example.com',
        contact: '1234567890',
      },
      theme: {
        color: '#3399cc',
      },
    };

    const rzp = new window.Razorpay(options);
    rzp.open();
  };

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <h1 className="text-2xl font-bold mb-4">Donation Page: {campaignTitle}</h1>

      <div className="space-y-4 mb-6">
        <h2 className="text-xl font-semibold">Items for Donation</h2>
        {items.map(item => (
          <div key={item.id} className="bg-white p-4 rounded-lg shadow-md">
            <h3 className="text-lg font-semibold">{item.name}</h3>
            <p>Price: ₹{item.price}</p>
            <input
              type="number"
              min="1"
              value={quantity}
              onChange={handleQuantityChange}
              className="w-16 p-1 border rounded"
            />
            <button
              onClick={() => handleAddToCart(item)}
              className="ml-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
            >
              Add to Cart
            </button>
          </div>
        ))}
      </div>

      <div className="bg-white p-4 rounded-lg shadow-md">
        <h2 className="text-xl font-semibold mb-4">Your Cart</h2>
        {cart.length === 0 ? (
          <p>Your cart is empty</p>
        ) : (
          <div>
            {cart.map(item => (
              <div key={item.id} className="flex justify-between items-center mb-2">
                <span>{item.name} (x{item.quantity}) - ₹{item.price * item.quantity}</span>
                <button
                  onClick={() => handleRemoveFromCart(item.id)}
                  className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded"
                >
                  Remove
                </button>
              </div>
            ))}
            <div className="mt-4">
              <button
                onClick={handlePayment}
                className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
              >
                Proceed to Payment
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default DonationPage;
