
A Java Stream is intrinsically linked to its source. It represents a sequence of elements from a data-providing source, such as a Collection, array, generator function, or I/O channel.
However, it is important to understand that a Stream does not store the elements itself; it is a pipeline for processing elements from the source. This linkage means: 
- Lazy Evaluation:
    Operations on a stream are not performed immediately when defined. Instead, they are executed only when a terminal operation (like collect(), forEach(), or count()) is invoked, and elements are consumed from the source only as needed.
- Single Use:
    A stream can be consumed only once. After a terminal operation is performed, the stream is considered consumed and cannot be reused. Attempting to operate on a consumed stream will result in an IllegalStateException.
- No Modification of Source:
    Stream operations are designed to be functional and do not modify the original source data. They produce a new stream or a result based on the transformations applied to the elements.



#### Here are the key Stream methods you should know:

### 1. Creation Methods
```java
// From Collection
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream();

// From array
String[] arr = {"a", "b", "c"};
Stream<String> stream = Arrays.stream(arr);

// Direct creation
Stream<String> stream = Stream.of("a", "b", "c");
```

### 2. Intermediate Operations
```java
// Filter
.filter(x -> condition)

// Map
.map(x -> transformation)
.mapToInt(x -> intValue)
.mapToDouble(x -> doubleValue)

// Sorted
.sorted()
.sorted(Comparator.reverseOrder())

// Distinct
.distinct()

// Peek (debugging)
.peek(x -> System.out.println(x))

// Skip/Limit
.skip(n)
.limit(n)
```

### 3. Terminal Operations
```java
// Collection operations
.collect(Collectors.toList())
.collect(Collectors.toSet())
.collect(Collectors.toMap(k -> key, v -> value))

// Reduction operations
.reduce(0, (a,b) -> a + b)
.count()
.sum() // for numeric streams
.average() // returns OptionalDouble
.min()
.max()

// Match operations
.anyMatch(predicate)
.allMatch(predicate)
.noneMatch(predicate)

// Find operations
.findFirst()
.findAny()

// ForEach
.forEach(action)
```

### 4. Specialized Streams
```java
// IntStream, DoubleStream, LongStream
IntStream.range(1, 5)
IntStream.rangeClosed(1, 5)

// Parallel streams
list.parallelStream()
stream.parallel()
```

### 5. Common Collectors
```java
// Collecting
.collect(Collectors.joining(", "))
.collect(Collectors.groupingBy(classifier))
.collect(Collectors.partitioningBy(predicate))
.collect(Collectors.summarizingInt(mapper))
```

Remember:
- Streams can only be used once
- Intermediate operations are lazy
- Terminal operations are eager
- Streams don't modify the original data source