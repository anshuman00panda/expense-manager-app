import './App.css';
import Header from './components/Header';
import ExpenseForm from './components/ExpenseForm';
import ExpenseTable from './components/ExpenseTable';
import Summary from './components/Summary';

function App() {
  return (
    <div className="App">
      <Header />
      <ExpenseForm />
      <ExpenseTable />
      <Summary />
    </div>
  );
}

export default App;
