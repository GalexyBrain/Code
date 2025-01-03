import pyshark

def detect_icmp_flood(pcap_file, threshold=1000):
    """
    Detect ICMP flooding attacks in a pcapng file.
    
    Parameters:
        pcap_file (str): Path to the pcapng file.
        threshold (int): Number of ICMP packets per second to consider as a flood.
    """
    try:
        # Load the pcap file
        capture = pyshark.FileCapture(pcap_file, display_filter='icmp')

        # Dictionary to store packet counts per second
        packet_counts = {}

        for packet in capture:
            try:
                # Extract timestamp
                timestamp = float(packet.sniff_timestamp)
                second = int(timestamp)  # Convert to seconds

                # Increment ICMP packet count for the second
                if second in packet_counts:
                    packet_counts[second] += 1
                else:
                    packet_counts[second] = 1
            except AttributeError:
                # Skip packets that do not have the expected attributes
                continue

        capture.close()

        # Analyze packet counts for potential flooding
        flood_detected = False
        for second, count in packet_counts.items():
            if count > threshold:
                print(f"Flood detected: {count} ICMP packets in second {second}.")
                flood_detected = True

        if not flood_detected:
            print("No ICMP flood detected.")

    except Exception as e:
        print(f"Error: {e}")

# Example usage
pcapng_file = 'data1.pcapng'  # Replace with your .pcapng file path
detect_icmp_flood(pcapng_file, threshold=100)
