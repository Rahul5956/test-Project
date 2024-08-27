import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter as Router } from 'react-router-dom';  // Import BrowserRouter
import MainRouter from './MainRouter'; // Update import path if necessary
import './index.css';

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Router>
      <MainRouter />
    </Router>
  </StrictMode>
);



