import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function WelcomePage() {
  const navigate = useNavigate();

  const [token, setToken] = useState(null);
  const [userEmail, setUserEmail] = useState(null);

  const [segments, setSegments] = useState([]);
  const [segId, setSegmentId] = useState("");

  const [manufacturers, setManufacturers] = useState([]);
  const [mfgId, setManufacturerId] = useState("");

  const [models, setModels] = useState([]);
  const [modelId, setModelId] = useState("");

  const [quantity, setQuantity] = useState("");
  const [minQty, setMinQty] = useState(1);

  // Initialize quantity from sessionStorage on mount
  useEffect(() => {
    const storedQty = sessionStorage.getItem("quantity");
    if (storedQty) {
      setQuantity(Number(storedQty));
    }
  }, []);

  // Update sessionStorage whenever quantity changes
  useEffect(() => {
    if (quantity !== "") {
      sessionStorage.setItem("quantity", quantity.toString());
    }
  }, [quantity]);

  const handleLogout = () => {
    sessionStorage.clear();
    alert("Logged out successfully.");
    navigate("/signin");
  };

  const handleAuthErrors = (res) => {
    if (res.status === 401 || res.status === 403) {
      alert("Session expired. Please login again.");
      handleLogout();
      return null;
    }
    return res;
  };

  useEffect(() => {
    const storedToken = sessionStorage.getItem("jwtToken");
    const storedEmail = sessionStorage.getItem("email");

    if (!storedToken) {
      alert("Session expired or not logged in. Please sign in.");
      navigate("/signin");
    } else {
      setToken(storedToken);
      setUserEmail(storedEmail);
    }
  }, [navigate]);

  // Fetch segments with normalization
  useEffect(() => {
    if (!token) return;

    const fetchSegments = async () => {
      try {
        const res = await fetch("https://localhost:7000/api/segments", {
          headers: { Authorization: `Bearer ${token}` },
        });

        const authCheckedRes = handleAuthErrors(res);
        if (!authCheckedRes) return;

        const text = await authCheckedRes.text();
        const data = text ? JSON.parse(text) : [];

        const normalized = data.map((s) => ({
          segId: s.SegId ?? s.segId,
          segName: s.SegName ?? s.segName,
        }));

        setSegments(normalized.filter((s) => s.segName !== "-- Select Vehicle Segment --"));
      } catch (error) {
        console.error("Failed to fetch segments:", error);
      }
    };

    fetchSegments();
  }, [token]);

  // Fetch manufacturers with normalization
  useEffect(() => {
    if (!segId || !token) {
      setManufacturers([]);
      setManufacturerId("");
      setModels([]);
      setModelId("");
      return;
    }

    const fetchManufacturers = async () => {
      try {
        const res = await fetch(`https://localhost:7000/api/manufacturers/seg/${segId}`, {
          headers: { Authorization: `Bearer ${token}` },
        });

        const authCheckedRes = handleAuthErrors(res);
        if (!authCheckedRes) return;

        const text = await authCheckedRes.text();
        const data = text ? JSON.parse(text) : [];

        const normalized = data.map((m) => ({
          mfgId: m.MfgId ?? m.mfgId,
          mfgName: m.MfgName ?? m.mfgName,
        }));

        setManufacturers(normalized);
      } catch (error) {
        console.error("Failed to fetch manufacturers:", error);
        setManufacturers([]);
      }
    };

    fetchManufacturers();
  }, [segId, token]);

  // Fetch models with normalization
  useEffect(() => {
    if (!mfgId || !segId || !token) {
      setModels([]);
      setModelId("");
      return;
    }

    const fetchModels = async () => {
      try {
        const res = await fetch(
          `https://localhost:7000/api/models/by-segment/${segId}/manufacturer/${mfgId}`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );

        const authCheckedRes = handleAuthErrors(res);
        if (!authCheckedRes) return;

        const text = await authCheckedRes.text();
        const data = text ? JSON.parse(text) : [];

        const normalized = data.map((mdl) => ({
          modelId: mdl.ModelId ?? mdl.modelId,
          modelName: mdl.ModelName ?? mdl.modelName,
          minQty: mdl.MinQty ?? mdl.minQty ?? 1,
        }));

        setModels(normalized);
        setModelId("");
      } catch (err) {
        console.error("Error fetching models:", err);
        setModels([]);
        setModelId("");
      }
    };

    fetchModels();
  }, [mfgId, segId, token]);

  const handleModelChange = (e) => {
    const selectedId = e.target.value;
    setModelId(selectedId);

    const selectedModel = models.find((mdl) => String(mdl.modelId) === selectedId);
    if (selectedModel) {
      setMinQty(selectedModel.minQty || 1);
      setQuantity(selectedModel.minQty || 1);
    } else {
      setMinQty(1);
      setQuantity("");
    }
  };

  const handleQuantityChange = (e) => {
    const enteredQty = parseInt(e.target.value, 10);
    if (enteredQty >= minQty) {
      setQuantity(enteredQty);
    } else {
      alert(`Quantity cannot be less than minimum required: ${minQty}`);
    }
  };

  if (!token) {
    return <h2 style={{ textAlign: "center", marginTop: "5rem" }}>Access Denied</h2>;
  }

  return (
    <div
      style={{
        minHeight: "90vh",
        width: "100vw",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        backgroundImage: `linear-gradient(rgba(255,255,255,0.0), rgba(255,255,255,0.3)), url('/images/w1.jpg')`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        backgroundRepeat: "no-repeat",
        backgroundAttachment: "fixed",
        fontFamily: "Arial, sans-serif",
      }}
    >
      <h1 style={{ fontSize: "2rem", color: "#222", marginBottom: "1.5rem" }}>
        Welcome to CarVisionX
      </h1>

      <div
        style={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem",
          padding: "2rem",
          borderRadius: "10px",
          backgroundColor: "rgba(255, 255, 255, 0.85)",
          boxShadow: "0 4px 8px rgba(0,0,0,0.1)",
          width: "90%",
          maxWidth: "500px",
          color: "#333",
        }}
      >
        <select value={segId} onChange={(e) => setSegmentId(e.target.value)} style={inputStyle}>
          <option value="">-- Select Vehicle Segment --</option>
          {segments.map((seg) => (
            <option key={seg.segId} value={seg.segId}>
              {seg.segName}
            </option>
          ))}
        </select>

        <select
          value={mfgId}
          onChange={(e) => setManufacturerId(e.target.value)}
          disabled={!segId}
          style={inputStyle}
        >
          <option value="">-- Select Manufacturer --</option>
          {Array.isArray(manufacturers) &&
            manufacturers.map((m) => (
              <option key={m.mfgId} value={m.mfgId}>
                {m.mfgName}
              </option>
            ))}
        </select>

        <select
          value={modelId}
          onChange={handleModelChange}
          disabled={!mfgId}
          style={inputStyle}
        >
          <option value="">-- Select Model --</option>
          {models.map((mdl) => (
            <option key={mdl.modelId} value={mdl.modelId}>
              {mdl.modelName}
            </option>
          ))}
        </select>

        <input
          type="number"
          value={quantity}
          onChange={handleQuantityChange}
          placeholder={`Min quantity: ${minQty}`}
          style={inputStyle}
          min={minQty}
        />

        <button
          onClick={() => {
            if (!segId || !mfgId || !modelId) {
              alert("Please select segment, manufacturer and model.");
              return;
            }
            if (!quantity || quantity < minQty) {
              alert(`Quantity must be at least ${minQty}`);
              return;
            }

            navigate("/configuration", {
              state: { segId, mfgId, modelId, quantity: Number(quantity) },
            });
          }}
          style={buttonStyle}
          onMouseOver={(e) => (e.target.style.backgroundColor = "#1e3c72")}
          onMouseOut={(e) => (e.target.style.backgroundColor = "#2a5298")}
        >
          Proceed
        </button>
      </div>
    </div>
  );
}

const inputStyle = {
  padding: "0.8rem",
  borderRadius: "6px",
  border: "1px solid #ccc",
  fontSize: "1rem",
};

const buttonStyle = {
  padding: "0.8rem",
  borderRadius: "6px",
  border: "none",
  fontWeight: "bold",
  backgroundColor: "#2a5298",
  color: "#fff",
  fontSize: "1rem",
  cursor: "pointer",
  transition: "background-color 0.3s",
};

export default WelcomePage;
