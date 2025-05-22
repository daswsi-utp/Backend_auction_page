import './style.css';

export default function Home() {
  return (
    <main className="dashboard">
      <div className="main-auction">
        <div className="product-image">
          <img
            src="https://cdn.pixabay.com/photo/2021/09/25/17/43/iphone-13-6655518_1280.jpg"
            alt="iPhone 13"
          />
        </div>
        <div className="product-info">
          <h1>📱 iPhone 13</h1>
          <p>
            Join the auction to win this iPhone 13 in excellent condition.
          </p>
          <div className="auction-details">
            <div>
              <span>Current bid</span>
              <strong>$420</strong>
            </div>
            <div>
              <span>Time remaining</span>
              <strong>08:45</strong>
            </div>
          </div>
          <div className="bid-input">
            <input type="number" placeholder="Your bid" />
            <button>Place Bid</button>
          </div>
          <div className="bid-history">
            <h3>📜 Recent Bids</h3>
            <ul>
              <li>
                <span>@andrea</span> — <strong>$420</strong>
              </li>
              <li>
                <span>@pepe</span> — <strong>$400</strong>
              </li>
              <li>
                <span>@luis</span> — <strong>$380</strong>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div className="other-auctions">
        <h2>🔎 Other Active Auctions</h2>
        <div className="auction-list">
          <div className="auction-card">
            <img
              src="https://cdn.pixabay.com/photo/2017/08/27/16/51/illuminated-keyboard-2686774_1280.jpg"
              alt="Laptop"
            />
            <p>💻 Dell Laptop</p>
            <span>$300</span>
          </div>
          <div className="auction-card">
            <img
              src="https://cdn.pixabay.com/photo/2020/02/10/09/18/calendar-4835848_1280.jpg"
              alt="Smartwatch"
            />
            <p>⌚ Samsung Smartwatch</p>
            <span>$90</span>
          </div>
          <div className="auction-card">
            <img
              src="https://cdn.pixabay.com/photo/2016/11/18/12/49/bicycle-1834265_1280.jpg"
              alt="Bicycle"
            />
            <p>🚲 Bicycle</p>
            <span>$220</span>
          </div>
        </div>
      </div>
    </main>
  );
}
