import './App.css';
import Header from './components/Header';
import ExpenseForm from './components/ExpenseForm';
import ExpenseTable from './components/ExpenseTable';
import Summary from './components/Summary';

function App() {

  const [expenses, setExpenses] = useState([
    {
      id: 1,
      title: "Groceries",
      category: "Food",
      amount: 50,
      date: "2025-01-01",
    },
    {
      id: 2,
      title: "Netflix Subscription",
      category: "Entertainment",
      amount: 15,
      date: "2025-01-02",
    },
  ]);

  const handleAddExpenses = (newExpense) => {
    setExpenses([...expenses, { ...newExpense, id: Date.now() }]);
  }; 

  const handleEditExpenses = (updatedExpense) => {
    setExpenses((prevExpenses) => 
      prevExpenses.map((expense) => 
        expense.id === updatedExpense.id ? updatedExpense : expense
      )
    );
  };

  const handleDeleteExpense = (id) => {
    setExpenses((prevExpenses) => prevExpenses.filter((expense) => expense.id !== id));
  };


  return (
    <div className="App">
      <Header />
      <ExpenseForm onSubmit={handleAddExpenses} />
      <ExpenseTable 
        expenses={expenses}
        onEdit={handleEditExpenses}
        onDelete={handleDeleteExpense}
      />
      <Summary />
    </div>
  );
}

export default App;
