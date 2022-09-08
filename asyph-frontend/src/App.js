import "./index.css";
import Navbar from "./components/Navbar";
import ContestBanner from "./components/ContestBanner";
import BestPerformers from "./components/BestPerformers";
function App() {
  return (
    <div className="App">
      <Navbar />
      <div className="row--div">
        <div className="col--div" style={{minWidth: "900px"}}>
          <ContestBanner />
        </div>
        <div className="col--div">
          <BestPerformers />
        </div>
      </div>
    </div>
  );
}

export default App;
